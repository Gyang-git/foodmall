package com.atghy.foodmall.order.dao;

import com.atghy.foodmall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收集订单信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-02 16:29:45
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
