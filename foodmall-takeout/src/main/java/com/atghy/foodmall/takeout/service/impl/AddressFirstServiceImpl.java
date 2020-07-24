package com.atghy.foodmall.takeout.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.takeout.dao.AddressFirstDao;
import com.atghy.foodmall.takeout.entity.AddressFirstEntity;
import com.atghy.foodmall.takeout.service.AddressFirstService;


@Service("addressFirstService")
public class AddressFirstServiceImpl extends ServiceImpl<AddressFirstDao, AddressFirstEntity> implements AddressFirstService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AddressFirstEntity> page = this.page(
                new Query<AddressFirstEntity>().getPage(params),
                new QueryWrapper<AddressFirstEntity>()
        );

        return new PageUtils(page);
    }

}