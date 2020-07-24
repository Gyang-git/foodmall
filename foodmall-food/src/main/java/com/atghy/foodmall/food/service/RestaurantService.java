package com.atghy.foodmall.food.service;

import com.atghy.foodmall.food.vo.RestaurantVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.food.entity.RestaurantEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 收集饭店信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
public interface RestaurantService extends IService<RestaurantEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addRestaurant(RestaurantVo restaurantVo);
}

