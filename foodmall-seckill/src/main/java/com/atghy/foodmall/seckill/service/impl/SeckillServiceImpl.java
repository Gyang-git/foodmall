package com.atghy.foodmall.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.atghy.foodmall.common.to.mq.SeckillOrderTo;
import com.atghy.foodmall.common.vo.CustomerResponseVo;
import com.atghy.foodmall.seckill.interceptor.LoginUserInterceptor;
import com.atghy.foodmall.seckill.service.SeckillService;
import com.atghy.foodmall.seckill.to.SeckillFoodRedisTo;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RSemaphore;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.redisson.api.RedissonClient;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-14
 * Description:
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    private final String SESSION_CACHE_PREFIX = "seckill:sessions:";
    private final String FOODKILL_CACHE_PREFIX = "seckill:foods:";
    private final String FOOD_STOCK_SEMAPHQRE = "seckill:stock:";

    @Override
    public List<SeckillFoodRedisTo> getCurrentSeckillFoods() {
        //redis确定当前时间拥有场次
        long time = new Date().getTime();
        Set<String> keys = redisTemplate.keys(SESSION_CACHE_PREFIX + "*");
        for (String key : keys) {
            //获取例子：seckill:sessions:1592848800000_1592852400000
            String replace = key.replace("seckill:session:", "");
            String[] s = replace.split("_");
            long start = Long.parseLong(s[0]);
            long end = Long.parseLong(s[1]);
            //限制时间区间
            if (time >= start && time <= end){
                List<String> range = redisTemplate.opsForList().range(key, -100, 100);
                BoundHashOperations<String,String,String> hashOps = redisTemplate.boundHashOps(FOODKILL_CACHE_PREFIX);
                List<String> list = hashOps.multiGet(range);
                if (list != null){
                    List<SeckillFoodRedisTo> collect = list.stream().map(item -> {
                        SeckillFoodRedisTo redisTo = JSON.parseObject((String) item, SeckillFoodRedisTo.class);
                        return redisTo;
                    }).collect(Collectors.toList());
                    return collect;
                }
                break;
            }
        }
        return null;
    }

    /**
     * 获取当前餐品秒杀信息
     * @param foodId
     * @return
     */
    @Override
    public SeckillFoodRedisTo getFoodSeckillInfo(Long foodId) {
        BoundHashOperations<String,String,String> hashOps = redisTemplate.boundHashOps(FOODKILL_CACHE_PREFIX);
        //所有需要参与秒杀的餐品key
        Set<String> keys = hashOps.keys();
        if (keys != null && keys.size() > 0){
            //正则匹配
            String regx = "//d_" + foodId;
            for (String key : keys) {
                String json = hashOps.get(key);
                SeckillFoodRedisTo foodRedisTo = JSON.parseObject(json, SeckillFoodRedisTo.class);
                //处理随机码
                //当前时间
                long current = new Date().getTime();
                if (current >= foodRedisTo.getStartTime() && current <= foodRedisTo.getEndTime()){
                }else{
                    foodRedisTo.setRandomCode(null);
                }
                return foodRedisTo;
            }
        }
        return null;
    }

    @Override
    public String kill(String killId, String key, Integer num) {
        //获取登录用户
        CustomerResponseVo customerResponseVo = LoginUserInterceptor.loginUser.get();
        //1-获取当前秒杀餐品的详细信息
        BoundHashOperations<String,String,String> hashOps = redisTemplate.boundHashOps(FOODKILL_CACHE_PREFIX);
        String json = hashOps.get(killId);
        if (StringUtils.isEmpty(json)){
            return null;
        }else{
            SeckillFoodRedisTo redisTo = JSON.parseObject(json, SeckillFoodRedisTo.class);
            //校验合法性
            Long startTime = redisTo.getStartTime();
            Long endTime = redisTo.getEndTime();
            long now = new Date().getTime();
            //时间差
            long ttl = endTime - startTime;
            if (now >= startTime && now <= endTime){
                //2-校验随机码和餐品id
                String randomCode = redisTo.getRandomCode();
                String rKey = redisTo.getPromotionSessionId() + "_" + redisTo.getFoodId();
                if (randomCode.equals(key) && killId.equals(rKey)){
                    //3-验证数量合理性
                    Integer seckillLimit = redisTo.getSeckillLimit();
                    if (num <= seckillLimit){
                        /**
                         * 4-验证该用户是否二次参与
                         * 幂等性限制 若秒杀成功->redis占位
                         * uuid_promotionSessionId_foodId
                         */
                        String isBuyKey = customerResponseVo.getUuid() + "_" + rKey;
                        //占位并设置自动过期为时间差
                        Boolean isBuy = redisTemplate.opsForValue().setIfAbsent(isBuyKey, num.toString(), ttl, TimeUnit.MICROSECONDS);
                        if (isBuy){
                            //占位成功->首次参与
                            RSemaphore semaphore = redissonClient.getSemaphore(FOOD_STOCK_SEMAPHQRE + randomCode);
                            //在指定的时间内获取许可 若失败则false
                            boolean b = semaphore.tryAcquire(num);
                            if (b){
                                //获取许可成功
                                //消息队列下单
                                String order_sn = IdWorker.getTimeId();
                                SeckillOrderTo orderTo = new SeckillOrderTo();
                                orderTo.setOrderSn(order_sn);
                                orderTo.setUuid(customerResponseVo.getUuid());
                                orderTo.setNum(num);
                                orderTo.setPromotionSessionId(redisTo.getPromotionSessionId());
                                orderTo.setFood_id(redisTo.getFoodId());
                                orderTo.setSeckillPrice(redisTo.getSeckillPrice());
                                //消息队列发送
                                rabbitTemplate.convertAndSend("order-event-exchange","order.seckill.order",orderTo);
                                return order_sn;
                            }
                            return null;
                        }else{
                            //占位失败 非首次参与
                            return null;
                        }
                    }
                }else{
                    return null;
                }
            }else{
                return null;
            }
        }
        return null;
    }
}
