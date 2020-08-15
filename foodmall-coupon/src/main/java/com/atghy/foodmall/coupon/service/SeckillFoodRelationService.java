package com.atghy.foodmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.coupon.entity.SeckillFoodRelationEntity;

import java.util.Map;

/**
 * 秒杀活动餐品关联
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-14 20:32:22
 */
public interface SeckillFoodRelationService extends IService<SeckillFoodRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

