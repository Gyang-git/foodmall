package com.atghy.foodmall.seckill.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-19
 * Description:
 */
@Configuration
public class MyRedissonConfig {
    @Bean
    public RedissonClient redisson(){
        Config config = new Config();
        //启用单节点模式
        config.useSingleServer().setAddress("redis://192.168.252.128:6379");
        //根据config创建RedissonClient实例
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
