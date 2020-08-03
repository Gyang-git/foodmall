package com.atghy.foodmall.order.vo;

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
    private Long skuId;

    private String name;

    private String title;

    private String imgUrl;

    private BigDecimal price;

    private Integer count;

    private BigDecimal totalPrice;

    private String categoryName;
}
