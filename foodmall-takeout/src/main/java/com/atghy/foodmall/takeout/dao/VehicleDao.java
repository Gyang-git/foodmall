package com.atghy.foodmall.takeout.dao;

import com.atghy.foodmall.takeout.entity.VehicleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收集外卖员所有车辆
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@Mapper
public interface VehicleDao extends BaseMapper<VehicleEntity> {
	
}
