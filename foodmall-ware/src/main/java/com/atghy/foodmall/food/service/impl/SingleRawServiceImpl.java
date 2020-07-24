package com.atghy.foodmall.food.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.food.dao.SingleRawDao;
import com.atghy.foodmall.food.entity.SingleRawEntity;
import com.atghy.foodmall.food.service.SingleRawService;


@Service("singleRawService")
public class SingleRawServiceImpl extends ServiceImpl<SingleRawDao, SingleRawEntity> implements SingleRawService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SingleRawEntity> page = this.page(
                new Query<SingleRawEntity>().getPage(params),
                new QueryWrapper<SingleRawEntity>()
        );

        return new PageUtils(page);
    }

}