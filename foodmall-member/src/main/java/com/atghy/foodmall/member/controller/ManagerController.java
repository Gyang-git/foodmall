package com.atghy.foodmall.member.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atghy.foodmall.member.entity.ManagerEntity;
import com.atghy.foodmall.member.service.ManagerService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集餐饮负责人个人信息及饭店关联
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-19 10:32:49
 */
@RestController
@RequestMapping("member/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:manager:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = managerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:manager:info")
    public R info(@PathVariable("id") Long id){
		ManagerEntity manager = managerService.getById(id);

        return R.ok().put("manager", manager);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:manager:save")
    public R save(@RequestBody ManagerEntity manager){
		managerService.save(manager);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:manager:update")
    public R update(@RequestBody ManagerEntity manager){
		managerService.updateById(manager);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:manager:delete")
    public R delete(@RequestBody Long[] ids){
		managerService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    //删除单个
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody Long id){
        managerService.removeById(id);
        return R.ok();
    }
}
