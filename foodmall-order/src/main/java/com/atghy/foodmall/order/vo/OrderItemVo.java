package com.atghy.foodmall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-02
 * Description: 订单项
 */
@Data
public class OrderItemVo {
    private Long skuId;

    private String name;

    private String title;

    private String foodType;

    private String imgUrl;

    private String stock;

    private String restaurantName;

    private BigDecimal price;

    private Integer count;

    private BigDecimal totalPrice;

    private String categoryName;
}
