package com.atghy.foodmall.seckill.scheduled;

import com.atghy.foodmall.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-19
 * Description:
 * 定时任务-秒杀商品上架
 */
@Slf4j
@Service
public class SeckillFoodScheduled {
    private final String upload_lock = "seckill:upload:lock";

    @Autowired
    SeckillService seckillService;

    @Autowired
    RedissonClient redissonClient;

    //每晚3点
    @Scheduled(cron = "0/10 * * * * ?")
    public void uploadSeckillFoodLates3Days(){
        //重复上架无需处理
        log.warn("上架秒杀商品");
        //分布式锁-当锁的业务执行完毕后状态已更新 释放锁其他线程取得锁得到最新状态
        RLock lock = redissonClient.getLock(upload_lock);
        //锁十秒
        lock.lock(10, TimeUnit.SECONDS);
        seckillService.uploadSeckillFoodLates3Days();
    }
}
