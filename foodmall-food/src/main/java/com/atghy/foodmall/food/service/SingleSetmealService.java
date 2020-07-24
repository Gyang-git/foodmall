package com.atghy.foodmall.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.food.entity.SingleSetmealEntity;

import java.util.Map;

/**
 * 收集套餐中单菜品的信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
public interface SingleSetmealService extends IService<SingleSetmealEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

