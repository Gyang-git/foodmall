package com.atghy.foodmall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.member.dao.ChefDao;
import com.atghy.foodmall.member.entity.ChefEntity;
import com.atghy.foodmall.member.service.ChefService;


@Service("chefService")
public class ChefServiceImpl extends ServiceImpl<ChefDao, ChefEntity> implements ChefService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ChefEntity> page = this.page(
                new Query<ChefEntity>().getPage(params),
                new QueryWrapper<ChefEntity>()
        );

        return new PageUtils(page);
    }

}