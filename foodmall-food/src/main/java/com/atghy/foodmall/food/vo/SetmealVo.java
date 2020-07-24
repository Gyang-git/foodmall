package com.atghy.foodmall.food.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-24
 * Description:
 */
@Data
public class SetmealVo {
    private String restaurantName;
    private String name;
    private BigDecimal price;
    private String imgUrl;
    private String title;
    private String subtitle;
    private String desc;
    private Integer comNum;
    private Integer useStatus;
    private Integer status;
    private SetMealCom setMealCom;
}
