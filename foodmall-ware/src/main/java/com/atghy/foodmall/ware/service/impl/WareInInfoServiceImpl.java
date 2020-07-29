package com.atghy.foodmall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.ware.dao.WareInInfoDao;
import com.atghy.foodmall.ware.entity.WareInInfoEntity;
import com.atghy.foodmall.ware.service.WareInInfoService;


@Service("wareInInfoService")
public class WareInInfoServiceImpl extends ServiceImpl<WareInInfoDao, WareInInfoEntity> implements WareInInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareInInfoEntity> page = this.page(
                new Query<WareInInfoEntity>().getPage(params),
                new QueryWrapper<WareInInfoEntity>()
        );

        return new PageUtils(page);
    }

}