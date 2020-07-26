package com.atghy.foodmall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.member.dao.ManagerDao;
import com.atghy.foodmall.member.entity.ManagerEntity;
import com.atghy.foodmall.member.service.ManagerService;


@Service("managerService")
public class ManagerServiceImpl extends ServiceImpl<ManagerDao, ManagerEntity> implements ManagerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ManagerEntity> page = this.page(
                new Query<ManagerEntity>().getPage(params),
                new QueryWrapper<ManagerEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ManagerEntity getEntityById(Long id) {
        ManagerEntity managerEntity = this.baseMapper.selectOne(new QueryWrapper<ManagerEntity>().eq("restaurant_id", id));
        return managerEntity;
    }

}