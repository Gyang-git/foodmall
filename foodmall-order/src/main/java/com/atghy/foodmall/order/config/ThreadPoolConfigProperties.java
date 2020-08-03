package com.atghy.foodmall.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-22
 * Description:线程池注册参数
 */
@ConfigurationProperties(prefix = "foodmall.thread")
@Component
@Data
public class ThreadPoolConfigProperties {
    private Integer coreSize;
    private Integer maxSize;
    private Integer keepAliveTime;
}
