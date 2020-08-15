package com.atghy.foodmall.food.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-02
 * Description:
 */
@Data
public class OrderItemVo {
    private Long skuId; //v

    private String name; //v

    private String title;

    private String foodType;

    private String imgUrl;

    private String stock;

    private String restaurantName;

    private BigDecimal price;

    private Integer count; //v

    private BigDecimal totalPrice;

    private String categoryName;
}
