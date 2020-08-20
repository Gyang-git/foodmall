package com.atghy.foodmall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.member.dao.WorkerNameDao;
import com.atghy.foodmall.member.entity.WorkerNameEntity;
import com.atghy.foodmall.member.service.WorkerNameService;


@Service("workerNameService")
public class WorkerNameServiceImpl extends ServiceImpl<WorkerNameDao, WorkerNameEntity> implements WorkerNameService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorkerNameEntity> page = this.page(
                new Query<WorkerNameEntity>().getPage(params),
                new QueryWrapper<WorkerNameEntity>()
        );

        return new PageUtils(page);
    }

}