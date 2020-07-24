package com.atghy.foodmall.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.food.entity.RestaurantAgentEntity;

import java.util.Map;

/**
 * 收集饭店与供应商的关联信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
public interface RestaurantAgentService extends IService<RestaurantAgentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

