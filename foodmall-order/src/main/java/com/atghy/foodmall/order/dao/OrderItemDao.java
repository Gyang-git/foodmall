package com.atghy.foodmall.order.dao;

import com.atghy.foodmall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收集订单包含餐品
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-06 21:32:15
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
