package com.atghy.foodmall.member.dao;

import com.atghy.foodmall.member.entity.ManagerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收集餐饮负责人个人信息及饭店关联
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-19 10:32:49
 */
@Mapper
public interface ManagerDao extends BaseMapper<ManagerEntity> {
	
}
