package com.atghy.foodmall.order.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.order.entity.HealthMonitoringEntity;
import com.atghy.foodmall.order.service.HealthMonitoringService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-02 16:29:45
 */
@RestController
@RequestMapping("order/healthmonitoring")
public class HealthMonitoringController {
    @Autowired
    private HealthMonitoringService healthMonitoringService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:healthmonitoring:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = healthMonitoringService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:healthmonitoring:info")
    public R info(@PathVariable("id") Long id){
		HealthMonitoringEntity healthMonitoring = healthMonitoringService.getById(id);

        return R.ok().put("healthMonitoring", healthMonitoring);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:healthmonitoring:save")
    public R save(@RequestBody HealthMonitoringEntity healthMonitoring){
		healthMonitoringService.save(healthMonitoring);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:healthmonitoring:update")
    public R update(@RequestBody HealthMonitoringEntity healthMonitoring){
		healthMonitoringService.updateById(healthMonitoring);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:healthmonitoring:delete")
    public R delete(@RequestBody Long[] ids){
		healthMonitoringService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
