package com.atghy.foodmall.cart.config;

import com.atghy.foodmall.cart.interpector.CartInterpector;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-29
 * Description:
 */
@Configuration
public class FoodmallWebConfig implements WebMvcConfigurer {
    //添加拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CartInterpector()).addPathPatterns("/**");
    }
}
