package com.atghy.foodmall.seckill.vo;

import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-14
 * Description:
 */
public class FoodInfo {
    /**
     * 单菜品id
     */
    private Long foodId;
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
    /**
     * 上架状态
     */
    private Integer useStatus;
}
