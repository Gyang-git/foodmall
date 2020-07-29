package com.atghy.foodmall.ware.dao;

import com.atghy.foodmall.ware.entity.WareInInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 记录向供应商采购的信息表
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-25 10:38:18
 */
@Mapper
public interface WareInInfoDao extends BaseMapper<WareInInfoEntity> {
	
}
