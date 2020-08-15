package com.atghy.foodmall.common.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-15
 * Description:
 */
@Data
public class singleVo {

    /**
     * 单菜品id
     */
    private Long id;
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
    /**
     * 启用状态
     */
    private Integer status;
}
