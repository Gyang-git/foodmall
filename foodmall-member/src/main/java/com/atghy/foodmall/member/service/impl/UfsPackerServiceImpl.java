package com.atghy.foodmall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.member.dao.UfsPackerDao;
import com.atghy.foodmall.member.entity.UfsPackerEntity;
import com.atghy.foodmall.member.service.UfsPackerService;


@Service("ufsPackerService")
public class UfsPackerServiceImpl extends ServiceImpl<UfsPackerDao, UfsPackerEntity> implements UfsPackerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UfsPackerEntity> page = this.page(
                new Query<UfsPackerEntity>().getPage(params),
                new QueryWrapper<UfsPackerEntity>()
        );

        return new PageUtils(page);
    }

}