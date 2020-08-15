package com.atghy.foodmall.order.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.order.entity.WorkerHealthEntity;
import com.atghy.foodmall.order.service.WorkerHealthService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 记录员工每日健康状况
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-15 22:07:08
 */
@RestController
@RequestMapping("order/workerhealth")
public class WorkerHealthController {
    @Autowired
    private WorkerHealthService workerHealthService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:workerhealth:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = workerHealthService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:workerhealth:info")
    public R info(@PathVariable("id") Long id){
		WorkerHealthEntity workerHealth = workerHealthService.getById(id);

        return R.ok().put("workerHealth", workerHealth);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:workerhealth:save")
    public R save(@RequestBody WorkerHealthEntity workerHealth){
		workerHealthService.save(workerHealth);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:workerhealth:update")
    public R update(@RequestBody WorkerHealthEntity workerHealth){
		workerHealthService.updateById(workerHealth);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:workerhealth:delete")
    public R delete(@RequestBody Long[] ids){
		workerHealthService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
