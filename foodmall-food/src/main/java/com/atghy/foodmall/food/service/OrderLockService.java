package com.atghy.foodmall.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.food.entity.OrderLockEntity;

import java.util.List;
import java.util.Map;

/**
 * 库存锁定数量
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-08 20:11:32
 */
public interface OrderLockService extends IService<OrderLockEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<OrderLockEntity> getOrderLockByOrderSn(Long orderSn);
}

