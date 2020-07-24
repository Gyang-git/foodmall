package com.atghy.foodmall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.member.dao.TakemanDao;
import com.atghy.foodmall.member.entity.TakemanEntity;
import com.atghy.foodmall.member.service.TakemanService;


@Service("takemanService")
public class TakemanServiceImpl extends ServiceImpl<TakemanDao, TakemanEntity> implements TakemanService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        System.out.println(params);
        IPage<TakemanEntity> page = this.page(
                new Query<TakemanEntity>().getPage(params),
                new QueryWrapper<TakemanEntity>()
        );
        return new PageUtils(page);
    }

}