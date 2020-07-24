package com.atghy.foodmall.takeout.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atghy.foodmall.takeout.entity.VehicleEntity;
import com.atghy.foodmall.takeout.service.VehicleService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集外卖员所有车辆
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@RestController
@RequestMapping("takeout/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;


    //维修
    @PostMapping("/maintainVehicle")
    public R maintainVehicle(@RequestBody Long id){
        vehicleService.maintainVehicle(id);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("takeout:vehicle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = vehicleService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("takeout:vehicle:info")
    public R info(@PathVariable("id") Long id){
		VehicleEntity vehicle = vehicleService.getById(id);

        return R.ok().put("vehicle", vehicle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("takeout:vehicle:save")
    public R save(@RequestBody VehicleEntity vehicle){
		vehicleService.save(vehicle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("takeout:vehicle:update")
    public R update(@RequestBody VehicleEntity vehicle){
		vehicleService.updateById(vehicle);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("takeout:vehicle:delete")
    public R delete(@RequestBody Long[] ids){
		vehicleService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    //删除单个
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody Long id){
        vehicleService.removeById(id);
        return R.ok();
    }
}
