package com.atghy.foodmall.order.dao;

import com.atghy.foodmall.order.entity.WorkerHealthEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 记录员工每日健康状况
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-15 22:07:08
 */
@Mapper
public interface WorkerHealthDao extends BaseMapper<WorkerHealthEntity> {
	
}
