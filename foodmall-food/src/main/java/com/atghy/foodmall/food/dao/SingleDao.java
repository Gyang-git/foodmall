package com.atghy.foodmall.food.dao;

import com.atghy.foodmall.food.entity.SingleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 收集所有单菜品表
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-23 14:14:27
 */
@Mapper
public interface SingleDao extends BaseMapper<SingleEntity> {

    Long getSkuStock(@Param("singleEntityDbName") String singleEntityDbName, @Param("singleId") Long singleId);
}
