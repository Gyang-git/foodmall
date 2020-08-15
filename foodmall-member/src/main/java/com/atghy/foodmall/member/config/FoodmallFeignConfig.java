package com.atghy.foodmall.member.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-02
 * Description:
 */
@Configuration
public class FoodmallFeignConfig {
    /**
     * Feign远程调用会丢请求头
     * 配置Feign远程调用的请求拦截器
     * 需手动同步请求头
     * @return
     */
    @Bean("requestInterceptor")
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
                if (attributes != null){
                    HttpServletRequest request = attributes.getRequest();
                    if (request != null){
                        //同步请求头数据
                        String cookie = request.getHeader("Cookie");
                        requestTemplate.header("Cookie",cookie);
                    }
                }
            }
        };
    }
}
