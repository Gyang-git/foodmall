package com.atghy.foodmall.food.service;

import com.atghy.foodmall.common.to.mq.OrderTo;
import com.atghy.foodmall.common.to.mq.StockLockedTo;
import com.atghy.foodmall.food.vo.WareSkuLockVo;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-07
 * Description:
 */
public interface FoodStockService {
    /**
     * 锁定订单库存
     * @param lockVo
     * @return
     */
    Boolean orderLockStock(WareSkuLockVo lockVo);

    /**
     * 解锁订单库存
     * @param to
     */
    void unlockStock(StockLockedTo to);

    /**
     * 订单关闭而解锁库存
     * @param orderTo
     */
    void unlockStock(OrderTo orderTo);
}
