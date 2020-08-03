package com.atghy.foodmall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.coupon.dao.UserScoreInfoDao;
import com.atghy.foodmall.coupon.entity.UserScoreInfoEntity;
import com.atghy.foodmall.coupon.service.UserScoreInfoService;


@Service("userScoreInfoService")
public class UserScoreInfoServiceImpl extends ServiceImpl<UserScoreInfoDao, UserScoreInfoEntity> implements UserScoreInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserScoreInfoEntity> page = this.page(
                new Query<UserScoreInfoEntity>().getPage(params),
                new QueryWrapper<UserScoreInfoEntity>()
        );

        return new PageUtils(page);
    }

}