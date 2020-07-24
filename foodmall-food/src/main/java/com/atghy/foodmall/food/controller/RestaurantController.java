package com.atghy.foodmall.food.controller;

import java.util.*;


import com.atghy.foodmall.food.vo.RestaurantVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atghy.foodmall.food.entity.RestaurantEntity;
import com.atghy.foodmall.food.service.RestaurantService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集饭店信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
@RestController
@RequestMapping("food/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    /**
     * 新增店面信息
     */
    @RequestMapping("/addRestaurant")
    public R addRestaurant(@RequestBody RestaurantVo restaurantVo){
        restaurantService.addRestaurant(restaurantVo);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("food:restaurant:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = restaurantService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("food:restaurant:info")
    public R info(@PathVariable("id") Long id){
		RestaurantEntity restaurant = restaurantService.getById(id);

        return R.ok().put("restaurant", restaurant);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("food:restaurant:save")
    public R save(@RequestBody RestaurantEntity restaurant){
		restaurantService.save(restaurant);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("food:restaurant:update")
    public R update(@RequestBody RestaurantEntity restaurant){
		restaurantService.updateById(restaurant);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("food:restaurant:delete")
    public R delete(@RequestBody Long[] ids){
		restaurantService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
