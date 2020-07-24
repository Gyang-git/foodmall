package com.atghy.foodmall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FoodmallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodmallMemberApplication.class, args);
    }

}
