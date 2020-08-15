package com.atghy.foodmall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.member.dao.PackerDao;
import com.atghy.foodmall.member.entity.PackerEntity;
import com.atghy.foodmall.member.service.PackerService;


@Service("packerService")
public class PackerServiceImpl extends ServiceImpl<PackerDao, PackerEntity> implements PackerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PackerEntity> page = this.page(
                new Query<PackerEntity>().getPage(params),
                new QueryWrapper<PackerEntity>()
        );

        return new PageUtils(page);
    }

}