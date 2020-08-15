package com.atghy.foodmall.food.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.food.entity.OrderLockEntity;
import com.atghy.foodmall.food.service.OrderLockService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 库存锁定数量
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-08 20:11:32
 */
@RestController
@RequestMapping("food/orderlock")
public class OrderLockController {
    @Autowired
    private OrderLockService orderLockService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("food:orderlock:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderLockService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("food:orderlock:info")
    public R info(@PathVariable("id") Long id){
		OrderLockEntity orderLock = orderLockService.getById(id);

        return R.ok().put("orderLock", orderLock);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("food:orderlock:save")
    public R save(@RequestBody OrderLockEntity orderLock){
		orderLockService.save(orderLock);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("food:orderlock:update")
    public R update(@RequestBody OrderLockEntity orderLock){
		orderLockService.updateById(orderLock);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("food:orderlock:delete")
    public R delete(@RequestBody Long[] ids){
		orderLockService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
