package com.atghy.foodmall.order.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.atghy.foodmall.common.constant.OrderConstant;
import com.atghy.foodmall.common.constant.OrderEnumConstant;
import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.common.vo.CustomerResponseVo;
import com.atghy.foodmall.order.feign.CartFeignService;
import com.atghy.foodmall.order.feign.FoodFeignService;
import com.atghy.foodmall.order.feign.takeoutFeignService;
import com.atghy.foodmall.order.interceptor.LoginUserInterceptor;
import com.atghy.foodmall.order.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.order.dao.OrderDao;
import com.atghy.foodmall.order.entity.OrderEntity;
import com.atghy.foodmall.order.service.OrderService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


@Slf4j
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    private ThreadLocal<OrderSubmitVo> submitVoThreadLocal = new InheritableThreadLocal<>();

    @Autowired
    CartFeignService cartFeignService;

    @Autowired
    FoodFeignService foodFeignService;

    @Autowired
    takeoutFeignService takeoutFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );
        return new PageUtils(page);
    }

    //TODO 异步实现
    @Override
    public OrderConfirmVo confirmOrder(){
        log.info(Thread.currentThread().getId() + "<---方法执行前线程为");
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        CustomerResponseVo customerResponseVo = LoginUserInterceptor.loginUser.get();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        RequestContextHolder.setRequestAttributes(requestAttributes);
        /**
         * 查询购物车所有的购物项
         * 检查库存状态
         */
        List<OrderItemVo> items = cartFeignService.getCurrentUserCartItems();
        confirmVo.setItems(items);
        //查询库存状态
        List<OrderItemVo> confirmItems = confirmVo.getItems();
        List<Long> collect = confirmItems.stream().map(item -> item.getSkuId()).collect(Collectors.toList());
        R r = foodFeignService.getSingleHasStock(collect);
        List<SkuStockVo> data = r.getData(new TypeReference<List<SkuStockVo>>() {
        });
        if (data != null){
            Map<Long, Boolean> map = data.stream().collect(Collectors.toMap(SkuStockVo::getSkuId, SkuStockVo::getHasStock));
            confirmVo.setStocks(map);
        }
        /**
         * 查询优惠信息 返回优惠折扣
         */
        Integer score = customerResponseVo.getScore();
        confirmVo.setScore(score);
        /**
         * 查询可选地址
         */
        List<AddressVo> address = takeoutFeignService.getAddress();
        confirmVo.setAddressList(address);
        //防重令牌
        String token = UUID.randomUUID().toString().replace("_", "");
        redisTemplate.opsForValue().set(OrderConstant.USER_ORDER_TOKEN_PREFIX + customerResponseVo.getUuid(),token,30, TimeUnit.MINUTES);
        confirmVo.setOrderToken(token);
        log.info(Thread.currentThread().getId() + "<---方法执行后线程为");
        return confirmVo;
    }

    /**
     * 提交订单功能
     * @param vo
     * @return
     */
    @Override
    @Transactional
    public SubmitOrderResponseVo submitOrder(OrderSubmitVo vo) {
        SubmitOrderResponseVo response = new SubmitOrderResponseVo();
        //更新状态码
        response.setCode(OrderEnumConstant.ORDER_SUBMITING.getCode());
        log.info(OrderEnumConstant.ORDER_SUBMITING.getMsg());
        submitVoThreadLocal.set(vo);

        //1-验证令牌
        //redis脚本
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        String orderToken = vo.getOrderToken();
        return null;
    }
}