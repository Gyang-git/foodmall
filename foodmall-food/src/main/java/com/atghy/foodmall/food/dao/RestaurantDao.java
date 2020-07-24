package com.atghy.foodmall.food.dao;

import com.atghy.foodmall.food.entity.RestaurantEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收集饭店信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
@Mapper
public interface RestaurantDao extends BaseMapper<RestaurantEntity> {
	
}
