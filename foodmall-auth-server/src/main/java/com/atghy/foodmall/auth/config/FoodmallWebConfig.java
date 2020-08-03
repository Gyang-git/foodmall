package com.atghy.foodmall.auth.config;

import org.springframework.context.annotation.Configuration;
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
    //视图映射

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("reg.html").setViewName("reg");
    }
}
