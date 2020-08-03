package com.atghy.foodmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.order.entity.HealthMonitoringEntity;

import java.util.Map;

/**
 * 
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-02 16:29:45
 */
public interface HealthMonitoringService extends IService<HealthMonitoringEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

