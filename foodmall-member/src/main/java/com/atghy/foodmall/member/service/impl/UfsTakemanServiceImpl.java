package com.atghy.foodmall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.member.dao.UfsTakemanDao;
import com.atghy.foodmall.member.entity.UfsTakemanEntity;
import com.atghy.foodmall.member.service.UfsTakemanService;


@Service("ufsTakemanService")
public class UfsTakemanServiceImpl extends ServiceImpl<UfsTakemanDao, UfsTakemanEntity> implements UfsTakemanService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UfsTakemanEntity> page = this.page(
                new Query<UfsTakemanEntity>().getPage(params),
                new QueryWrapper<UfsTakemanEntity>()
        );

        return new PageUtils(page);
    }

}