package com.atghy.foodmall.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FoodmallWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodmallWareApplication.class, args);
    }

}
