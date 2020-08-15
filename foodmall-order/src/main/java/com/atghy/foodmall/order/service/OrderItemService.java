package com.atghy.foodmall.order.service;

import com.atghy.foodmall.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.order.entity.OrderItemEntity;

import java.util.List;
import java.util.Map;

/**
 * 收集订单包含餐品
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-06 21:32:15
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<OrderItemEntity> getItemByOrderSn(String orderSn);
}

