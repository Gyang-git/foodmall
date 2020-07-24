package com.atghy.foodmall.food.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.food.dao.SingleSetmealDao;
import com.atghy.foodmall.food.entity.SingleSetmealEntity;
import com.atghy.foodmall.food.service.SingleSetmealService;


@Service("singleSetmealService")
public class SingleSetmealServiceImpl extends ServiceImpl<SingleSetmealDao, SingleSetmealEntity> implements SingleSetmealService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SingleSetmealEntity> page = this.page(
                new Query<SingleSetmealEntity>().getPage(params),
                new QueryWrapper<SingleSetmealEntity>()
        );

        return new PageUtils(page);
    }

}