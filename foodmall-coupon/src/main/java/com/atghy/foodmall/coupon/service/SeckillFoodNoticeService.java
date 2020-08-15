package com.atghy.foodmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.coupon.entity.SeckillFoodNoticeEntity;

import java.util.Map;

/**
 * 秒杀餐品通知订阅
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-14 20:32:22
 */
public interface SeckillFoodNoticeService extends IService<SeckillFoodNoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

