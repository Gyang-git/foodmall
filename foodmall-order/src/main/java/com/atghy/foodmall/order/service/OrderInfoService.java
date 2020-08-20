package com.atghy.foodmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.order.entity.OrderInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 记录订单详情信息 
包括派单信息与订单状态信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-14 18:57:17
 */
public interface OrderInfoService extends IService<OrderInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<OrderInfoEntity> getInfo(String orderSn);
}

