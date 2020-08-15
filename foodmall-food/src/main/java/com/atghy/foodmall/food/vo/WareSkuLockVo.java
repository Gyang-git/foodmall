package com.atghy.foodmall.food.vo;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-07
 * Description:
 */
@Data
public class WareSkuLockVo {
    //订单号
    private String orderSn;

    //订单项
    private List<OrderItemVo> locks;
}
