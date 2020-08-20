package com.atghy.foodmall.seckill.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-19
 * Description:
 */
@Data
public class SetmealInfoVo {
    /**
     *
     */
    private Long id;
    /**
     * 饭店名称 外联饭店
     */
    private String restaurantName;
    /**
     * 套餐名称
     */
    private String name;
    /**
     * 库存剩余
     */
    private Long quantity;
    /**
     * 锁定库存
     */
    private Long quantityLock;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 上架状态
     */
    private Integer useStatus;
    /**
     * 启用状态
     */
    private Integer status;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 主展示标题
     */
    private String title;
    /**
     * 副展示标题
     */
    private String subtitle;
    /**
     * 描述
     */
    private String desc;

    private Integer score;
}
