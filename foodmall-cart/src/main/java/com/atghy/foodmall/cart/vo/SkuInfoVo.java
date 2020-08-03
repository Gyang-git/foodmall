package com.atghy.foodmall.cart.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-31
 * Description:
 */
@Data
public class SkuInfoVo {
    private Long singleId;

    private Long setmealId;

    /**
     * 饭店名称 外联饭店
     */
    private String restaurantName;
    /**
     * 菜名 外联菜品特性
     */
    private String name;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 库存剩余
     */
    private Long quantity;
    /**
     * 锁定库存量
     */
    private Long quantityLock;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 主展示标题
     */
    private String title;
    /**
     * 副展示标题
     */
    private String subtitle;

}
