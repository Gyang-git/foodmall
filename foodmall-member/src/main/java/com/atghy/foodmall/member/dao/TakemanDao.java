package com.atghy.foodmall.member.dao;

import com.atghy.foodmall.member.entity.TakemanEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收集外卖员个人基础信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-19 10:32:49
 */
@Mapper
public interface TakemanDao extends BaseMapper<TakemanEntity> {
	
}
