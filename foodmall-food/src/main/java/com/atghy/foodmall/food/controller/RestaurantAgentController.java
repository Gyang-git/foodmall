package com.atghy.foodmall.food.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atghy.foodmall.food.entity.RestaurantAgentEntity;
import com.atghy.foodmall.food.service.RestaurantAgentService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集饭店与供应商的关联信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
@RestController
@RequestMapping("food/restaurantagent")
public class RestaurantAgentController {
    @Autowired
    private RestaurantAgentService restaurantAgentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("food:restaurantagent:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = restaurantAgentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("food:restaurantagent:info")
    public R info(@PathVariable("id") Long id){
		RestaurantAgentEntity restaurantAgent = restaurantAgentService.getById(id);

        return R.ok().put("restaurantAgent", restaurantAgent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("food:restaurantagent:save")
    public R save(@RequestBody RestaurantAgentEntity restaurantAgent){
		restaurantAgentService.save(restaurantAgent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("food:restaurantagent:update")
    public R update(@RequestBody RestaurantAgentEntity restaurantAgent){
		restaurantAgentService.updateById(restaurantAgent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("food:restaurantagent:delete")
    public R delete(@RequestBody Long[] ids){
		restaurantAgentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    //删除单个
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody Long id){
        restaurantAgentService.removeById(id);
        return R.ok();
    }
}
