package com.atghy.foodmall.food.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.food.dao.RestaurantAgentDao;
import com.atghy.foodmall.food.entity.RestaurantAgentEntity;
import com.atghy.foodmall.food.service.RestaurantAgentService;


@Service("restaurantAgentService")
public class RestaurantAgentServiceImpl extends ServiceImpl<RestaurantAgentDao, RestaurantAgentEntity> implements RestaurantAgentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RestaurantAgentEntity> page = this.page(
                new Query<RestaurantAgentEntity>().getPage(params),
                new QueryWrapper<RestaurantAgentEntity>()
        );

        return new PageUtils(page);
    }

}