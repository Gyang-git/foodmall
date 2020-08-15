package com.atghy.foodmall.food.dao;

import com.atghy.foodmall.food.entity.OrderLockEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存锁定数量
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-08 20:11:32
 */
@Mapper
public interface OrderLockDao extends BaseMapper<OrderLockEntity> {
	
}
