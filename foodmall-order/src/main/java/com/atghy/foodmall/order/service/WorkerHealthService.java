package com.atghy.foodmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.order.entity.WorkerHealthEntity;

import java.util.Map;

/**
 * 记录员工每日健康状况
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-15 22:07:08
 */
public interface WorkerHealthService extends IService<WorkerHealthEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

