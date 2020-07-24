package com.atghy.foodmall.food.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.food.entity.SingleSetmealEntity;
import com.atghy.foodmall.food.service.SingleSetmealService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集套餐中单菜品的信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
@RestController
@RequestMapping("food/singlesetmeal")
public class SingleSetmealController {
    @Autowired
    private SingleSetmealService singleSetmealService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("food:singlesetmeal:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = singleSetmealService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("food:singlesetmeal:info")
    public R info(@PathVariable("id") Long id){
		SingleSetmealEntity singleSetmeal = singleSetmealService.getById(id);

        return R.ok().put("singleSetmeal", singleSetmeal);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("food:singlesetmeal:save")
    public R save(@RequestBody SingleSetmealEntity singleSetmeal){
		singleSetmealService.save(singleSetmeal);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("food:singlesetmeal:update")
    public R update(@RequestBody SingleSetmealEntity singleSetmeal){
		singleSetmealService.updateById(singleSetmeal);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("food:singlesetmeal:delete")
    public R delete(@RequestBody Long[] ids){
		singleSetmealService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
