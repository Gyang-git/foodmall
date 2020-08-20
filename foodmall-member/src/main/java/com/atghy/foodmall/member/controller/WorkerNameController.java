package com.atghy.foodmall.member.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.member.entity.WorkerNameEntity;
import com.atghy.foodmall.member.service.WorkerNameService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-16 21:29:12
 */
@RestController
@RequestMapping("member/workername")
public class WorkerNameController {
    @Autowired
    private WorkerNameService workerNameService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:workername:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = workerNameService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:workername:info")
    public R info(@PathVariable("id") Long id){
		WorkerNameEntity workerName = workerNameService.getById(id);

        return R.ok().put("workerName", workerName);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:workername:save")
    public R save(@RequestBody WorkerNameEntity workerName){
		workerNameService.save(workerName);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:workername:update")
    public R update(@RequestBody WorkerNameEntity workerName){
		workerNameService.updateById(workerName);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:workername:delete")
    public R delete(@RequestBody Long[] ids){
		workerNameService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
