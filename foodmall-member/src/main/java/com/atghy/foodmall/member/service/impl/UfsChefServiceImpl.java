package com.atghy.foodmall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.member.dao.UfsChefDao;
import com.atghy.foodmall.member.entity.UfsChefEntity;
import com.atghy.foodmall.member.service.UfsChefService;


@Service("ufsChefService")
public class UfsChefServiceImpl extends ServiceImpl<UfsChefDao, UfsChefEntity> implements UfsChefService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UfsChefEntity> page = this.page(
                new Query<UfsChefEntity>().getPage(params),
                new QueryWrapper<UfsChefEntity>()
        );

        return new PageUtils(page);
    }

}