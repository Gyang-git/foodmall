package com.atghy.foodmall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.order.dao.HealthMonitoringDao;
import com.atghy.foodmall.order.entity.HealthMonitoringEntity;
import com.atghy.foodmall.order.service.HealthMonitoringService;


@Service("healthMonitoringService")
public class HealthMonitoringServiceImpl extends ServiceImpl<HealthMonitoringDao, HealthMonitoringEntity> implements HealthMonitoringService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HealthMonitoringEntity> page = this.page(
                new Query<HealthMonitoringEntity>().getPage(params),
                new QueryWrapper<HealthMonitoringEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Long saveHealthMonitor(HealthMonitoringEntity healthMonitoringEntity) {
        int insert = baseMapper.insert(healthMonitoringEntity);
        return healthMonitoringEntity.getId();
    }

}