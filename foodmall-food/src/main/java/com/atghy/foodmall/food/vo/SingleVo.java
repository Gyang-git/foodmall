package com.atghy.foodmall.food.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-22
 * Description:
 */
@Data
public class SingleVo {
    private String restaurantName;
    private String agentName;
    private String name;
    private BigDecimal price;
    private String imgUrl;
    private String title;
    private String subtitle;
    private String desc;
    private Integer useStatus;
    private Integer status;
    private SingleNatureForm singleNatureForm;
    private SingleRawForm singleRawForm;
}
