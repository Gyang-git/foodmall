package com.atghy.foodmall.takeout.dao;

import com.atghy.foodmall.takeout.entity.OutInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 记录外卖派送信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@Mapper
public interface OutInfoDao extends BaseMapper<OutInfoEntity> {
	
}
