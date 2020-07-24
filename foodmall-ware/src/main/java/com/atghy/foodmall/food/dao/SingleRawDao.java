package com.atghy.foodmall.food.dao;

import com.atghy.foodmall.food.entity.SingleRawEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收集单菜品制作所需原料
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-22 16:53:54
 */
@Mapper
public interface SingleRawDao extends BaseMapper<SingleRawEntity> {
	
}
