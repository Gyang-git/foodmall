package com.atghy.foodmall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.coupon.dao.LevelStandardDao;
import com.atghy.foodmall.coupon.entity.LevelStandardEntity;
import com.atghy.foodmall.coupon.service.LevelStandardService;


@Service("levelStandardService")
public class LevelStandardServiceImpl extends ServiceImpl<LevelStandardDao, LevelStandardEntity> implements LevelStandardService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LevelStandardEntity> page = this.page(
                new Query<LevelStandardEntity>().getPage(params),
                new QueryWrapper<LevelStandardEntity>()
        );

        return new PageUtils(page);
    }

}