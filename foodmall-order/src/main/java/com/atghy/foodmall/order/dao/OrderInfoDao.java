package com.atghy.foodmall.order.dao;

import com.atghy.foodmall.order.entity.OrderInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 记录订单详情信息 
包括派单信息与订单状态信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-14 18:57:17
 */
@Mapper
public interface OrderInfoDao extends BaseMapper<OrderInfoEntity> {
	
}
