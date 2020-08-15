package com.atghy.foodmall.common.to.mq;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-07
 * Description:
 * 库存锁定数据 消息队列传递对象
 */
@Data
public class StockLockedTo {

    //锁定的信息
    private Long skuId;

    private OrderLockTo orderLockTo;
}
