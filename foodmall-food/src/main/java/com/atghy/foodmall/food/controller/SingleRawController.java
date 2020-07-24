package com.atghy.foodmall.food.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.food.entity.SingleRawEntity;
import com.atghy.foodmall.food.service.SingleRawService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集单菜品制作所需原料
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-22 17:46:42
 */
@RestController
@RequestMapping("food/singleraw")
public class SingleRawController {
    @Autowired
    private SingleRawService singleRawService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("food:singleraw:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = singleRawService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("food:singleraw:info")
    public R info(@PathVariable("id") Long id){
		SingleRawEntity singleRaw = singleRawService.getById(id);

        return R.ok().put("singleRaw", singleRaw);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("food:singleraw:save")
    public R save(@RequestBody SingleRawEntity singleRaw){
		singleRawService.save(singleRaw);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("food:singleraw:update")
    public R update(@RequestBody SingleRawEntity singleRaw){
		singleRawService.updateById(singleRaw);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("food:singleraw:delete")
    public R delete(@RequestBody Long[] ids){
		singleRawService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
