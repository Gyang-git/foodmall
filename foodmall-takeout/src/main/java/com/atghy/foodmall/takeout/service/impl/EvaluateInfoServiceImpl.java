package com.atghy.foodmall.takeout.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.takeout.dao.EvaluateInfoDao;
import com.atghy.foodmall.takeout.entity.EvaluateInfoEntity;
import com.atghy.foodmall.takeout.service.EvaluateInfoService;


@Service("evaluateInfoService")
public class EvaluateInfoServiceImpl extends ServiceImpl<EvaluateInfoDao, EvaluateInfoEntity> implements EvaluateInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EvaluateInfoEntity> page = this.page(
                new Query<EvaluateInfoEntity>().getPage(params),
                new QueryWrapper<EvaluateInfoEntity>()
        );

        return new PageUtils(page);
    }

}