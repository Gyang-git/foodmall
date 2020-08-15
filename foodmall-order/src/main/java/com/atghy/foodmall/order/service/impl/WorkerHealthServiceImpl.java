package com.atghy.foodmall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.order.dao.WorkerHealthDao;
import com.atghy.foodmall.order.entity.WorkerHealthEntity;
import com.atghy.foodmall.order.service.WorkerHealthService;


@Service("workerHealthService")
public class WorkerHealthServiceImpl extends ServiceImpl<WorkerHealthDao, WorkerHealthEntity> implements WorkerHealthService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorkerHealthEntity> page = this.page(
                new Query<WorkerHealthEntity>().getPage(params),
                new QueryWrapper<WorkerHealthEntity>()
        );

        return new PageUtils(page);
    }

}