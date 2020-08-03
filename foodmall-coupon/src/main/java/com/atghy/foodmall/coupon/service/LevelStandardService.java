package com.atghy.foodmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.coupon.entity.LevelStandardEntity;

import java.util.Map;

/**
 * 收集用户优惠标准
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-30 12:30:16
 */
public interface LevelStandardService extends IService<LevelStandardEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

