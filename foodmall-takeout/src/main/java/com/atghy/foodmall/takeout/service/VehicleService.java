package com.atghy.foodmall.takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.takeout.entity.VehicleEntity;

import java.util.List;
import java.util.Map;

/**
 * 收集外卖员所有车辆
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
public interface VehicleService extends IService<VehicleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void maintainVehicle(Long id);

    List<VehicleEntity> getUnUseVehicle();

}

