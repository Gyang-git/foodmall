package com.atghy.foodmall.food.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-24
 * Description:
 */
@Data
public class RestaurantVo {
    private String name;
    private String managerName;
    private Long level;
    private ManagerForm managerForm;
}
