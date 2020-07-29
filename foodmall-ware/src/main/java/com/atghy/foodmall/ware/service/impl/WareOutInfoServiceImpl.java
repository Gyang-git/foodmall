package com.atghy.foodmall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.ware.dao.WareOutInfoDao;
import com.atghy.foodmall.ware.entity.WareOutInfoEntity;
import com.atghy.foodmall.ware.service.WareOutInfoService;


@Service("wareOutInfoService")
public class WareOutInfoServiceImpl extends ServiceImpl<WareOutInfoDao, WareOutInfoEntity> implements WareOutInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareOutInfoEntity> page = this.page(
                new Query<WareOutInfoEntity>().getPage(params),
                new QueryWrapper<WareOutInfoEntity>()
        );

        return new PageUtils(page);
    }

}