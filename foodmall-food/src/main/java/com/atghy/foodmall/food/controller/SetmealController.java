package com.atghy.foodmall.food.controller;

import java.util.Arrays;
import java.util.Map;


import com.atghy.foodmall.food.vo.SetmealVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.food.entity.SetmealEntity;
import com.atghy.foodmall.food.service.SetmealService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集套餐
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-21 14:09:51
 */
@RestController
@RequestMapping("food/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    /**
     * 新增套餐
     * @return
     */
    @RequestMapping("/addSetmeal")
    public R addSetmeal(@RequestBody SetmealVo setmealVo){
        setmealService.addSetmeal(setmealVo);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("food:setmeal:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = setmealService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("food:setmeal:info")
    public R info(@PathVariable("id") Long id){
		SetmealEntity setmeal = setmealService.getById(id);

        return R.ok().put("setmeal", setmeal);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("food:setmeal:save")
    public R save(@RequestBody SetmealEntity setmeal){
		setmealService.save(setmeal);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("food:setmeal:update")
    public R update(@RequestBody SetmealEntity setmeal){
		setmealService.updateById(setmeal);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("food:setmeal:delete")
    public R delete(@RequestBody Long[] ids){
		setmealService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
