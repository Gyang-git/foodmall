package com.atghy.foodmall.food.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.food.dao.NatureDao;
import com.atghy.foodmall.food.entity.NatureEntity;
import com.atghy.foodmall.food.service.NatureService;


@Service("natureService")
public class NatureServiceImpl extends ServiceImpl<NatureDao, NatureEntity> implements NatureService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NatureEntity> page = this.page(
                new Query<NatureEntity>().getPage(params),
                new QueryWrapper<NatureEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public NatureEntity getNatureByName(String name) {
        NatureEntity natureEntity = this.baseMapper.selectOne(new QueryWrapper<NatureEntity>().eq("single_name", name));
        return natureEntity;
    }
}