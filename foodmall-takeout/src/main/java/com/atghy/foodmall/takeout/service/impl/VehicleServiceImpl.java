package com.atghy.foodmall.takeout.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.takeout.dao.VehicleDao;
import com.atghy.foodmall.takeout.entity.VehicleEntity;
import com.atghy.foodmall.takeout.service.VehicleService;


@Service("vehicleService")
public class VehicleServiceImpl extends ServiceImpl<VehicleDao, VehicleEntity> implements VehicleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<VehicleEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)){
            queryWrapper.eq("id",key).or()
                    .like("vehicle_name",key).or()
                    .like("sort",key);
        }
        IPage<VehicleEntity> page = this.page(
                new Query<VehicleEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public void maintainVehicle(Long id) {
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(id);
        vehicleEntity.setOverhaulTime(new Date());
        this.baseMapper.updateById(vehicleEntity);
        System.out.println(new Date());
    }

    @Override
    public List<VehicleEntity> getUnUseVehicle() {
        QueryWrapper<VehicleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("use_status",0);
        List<VehicleEntity> entities = baseMapper.selectList(queryWrapper);
        return entities;
    }

}