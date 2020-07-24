package com.atghy.foodmall.takeout.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.takeout.dao.OutInfoDao;
import com.atghy.foodmall.takeout.entity.OutInfoEntity;
import com.atghy.foodmall.takeout.service.OutInfoService;


@Service("outInfoService")
public class OutInfoServiceImpl extends ServiceImpl<OutInfoDao, OutInfoEntity> implements OutInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OutInfoEntity> page = this.page(
                new Query<OutInfoEntity>().getPage(params),
                new QueryWrapper<OutInfoEntity>()
        );

        return new PageUtils(page);
    }

}