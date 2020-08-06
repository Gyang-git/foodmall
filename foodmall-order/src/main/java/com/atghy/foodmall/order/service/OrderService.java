package com.atghy.foodmall.order.service;

import com.atghy.foodmall.order.vo.OrderConfirmVo;
import com.atghy.foodmall.order.vo.OrderSubmitVo;
import com.atghy.foodmall.order.vo.SubmitOrderResponseVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 收集订单信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-02 16:29:45
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    OrderConfirmVo confirmOrder();

    SubmitOrderResponseVo submitOrder(OrderSubmitVo vo);
}

