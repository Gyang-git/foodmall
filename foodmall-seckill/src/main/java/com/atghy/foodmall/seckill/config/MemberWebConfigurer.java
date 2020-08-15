package com.atghy.foodmall.seckill.config;

import com.atghy.foodmall.seckill.interceptor.LoginUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-13
 * Description:
 */
public class MemberWebConfigurer implements WebMvcConfigurer {
    @Autowired
    LoginUserInterceptor loginUserInterceptor;

    //登录拦截器注册实现
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginUserInterceptor).addPathPatterns("/**").excludePathPatterns("/error").excludePathPatterns("/listWithItem");
    }
}
