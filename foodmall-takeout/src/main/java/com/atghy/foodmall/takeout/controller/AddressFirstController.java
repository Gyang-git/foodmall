package com.atghy.foodmall.takeout.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atghy.foodmall.takeout.entity.AddressFirstEntity;
import com.atghy.foodmall.takeout.service.AddressFirstService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 记录外卖可选地址一级分类
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@RestController
@RequestMapping("takeout/addressfirst")
public class AddressFirstController {
    @Autowired
    private AddressFirstService addressFirstService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("takeout:addressfirst:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = addressFirstService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("takeout:addressfirst:info")
    public R info(@PathVariable("id") Long id){
		AddressFirstEntity addressFirst = addressFirstService.getById(id);

        return R.ok().put("addressFirst", addressFirst);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("takeout:addressfirst:save")
    public R save(@RequestBody AddressFirstEntity addressFirst){
		addressFirstService.save(addressFirst);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("takeout:addressfirst:update")
    public R update(@RequestBody AddressFirstEntity addressFirst){
		addressFirstService.updateById(addressFirst);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("takeout:addressfirst:delete")
    public R delete(@RequestBody Long[] ids){
		addressFirstService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
