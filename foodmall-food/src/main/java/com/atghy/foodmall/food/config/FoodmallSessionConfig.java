package com.atghy.foodmall.food.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-31
 * Description: springSession 配置
 */
@Configuration
public class FoodmallSessionConfig {
    /**
     * 自定义cookie序列化
     * @return
     */
    @Bean
    public CookieSerializer cookieSerializer(){
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        //设置作用域 扩大到父域
        cookieSerializer.setDomainName("foodmall.com");
        //设置cookie名字
        cookieSerializer.setCookieName("FMSESSION");
        return cookieSerializer;
    }

    /**
     * 存储类型序列化 转json
     * @return
     */
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }
}
