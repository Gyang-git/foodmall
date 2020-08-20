package com.atghy.foodmall.member.dao;

import com.atghy.foodmall.member.entity.WorkerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收集厨师个人基础信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-16 14:39:12
 */
@Mapper
public interface WorkerDao extends BaseMapper<WorkerEntity> {
	
}
