package com.atghy.foodmall.food.controller;

import java.util.Arrays;
import java.util.Map;


import com.sun.javafx.logging.PulseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.food.entity.NatureEntity;
import com.atghy.foodmall.food.service.NatureService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集菜品特性 包括酸甜苦辣、油炸、口感(软硬)、冷热、鲜品 外联单菜品
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-23 11:48:38
 */
@RestController
@RequestMapping("food/nature")
public class NatureController {
    @Autowired
    private NatureService natureService;

    @RequestMapping("/getNatureByName/{name}")
    public R getNatureByName(@PathVariable("name")String name){
        NatureEntity natureEntity = natureService.getNatureByName(name);
        return R.ok().put("nature",natureEntity);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("food:nature:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = natureService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("food:nature:info")
    public R info(@PathVariable("id") Long id){
		NatureEntity nature = natureService.getById(id);

        return R.ok().put("nature", nature);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("food:nature:save")
    public R save(@RequestBody NatureEntity nature){
		natureService.save(nature);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("food:nature:update")
    public R update(@RequestBody NatureEntity nature){
		natureService.updateById(nature);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("food:nature:delete")
    public R delete(@RequestBody Long[] ids){
		natureService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
