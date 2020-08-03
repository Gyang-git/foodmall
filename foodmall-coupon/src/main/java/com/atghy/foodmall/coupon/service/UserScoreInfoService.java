package com.atghy.foodmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.coupon.entity.UserScoreInfoEntity;

import java.util.Map;

/**
 * 收集顾客积分情况
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-30 12:30:16
 */
public interface UserScoreInfoService extends IService<UserScoreInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

