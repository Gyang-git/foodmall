package com.atghy.foodmall.food.dao;

import com.atghy.foodmall.food.entity.NatureEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收集菜品特性 包括酸甜苦辣、油炸、口感(软硬)、冷热、鲜品 外联单菜品
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-23 11:48:38
 */
@Mapper
public interface NatureDao extends BaseMapper<NatureEntity> {
	
}
