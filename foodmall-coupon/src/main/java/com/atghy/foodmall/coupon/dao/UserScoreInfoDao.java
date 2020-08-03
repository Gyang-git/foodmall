package com.atghy.foodmall.coupon.dao;

import com.atghy.foodmall.coupon.entity.UserScoreInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收集顾客积分情况
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-30 12:30:16
 */
@Mapper
public interface UserScoreInfoDao extends BaseMapper<UserScoreInfoEntity> {
	
}
