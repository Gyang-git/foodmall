package com.atghy.foodmall.member.dao;

import com.atghy.foodmall.member.entity.ChefEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收集厨师个人基础信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-15 22:26:58
 */
@Mapper
public interface ChefDao extends BaseMapper<ChefEntity> {
	
}
