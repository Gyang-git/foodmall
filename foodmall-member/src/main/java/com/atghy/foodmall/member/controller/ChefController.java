package com.atghy.foodmall.member.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.member.entity.ChefEntity;
import com.atghy.foodmall.member.service.ChefService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集厨师个人基础信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-15 22:26:58
 */
@RestController
@RequestMapping("member/chef")
public class ChefController {
    @Autowired
    private ChefService chefService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:chef:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = chefService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:chef:info")
    public R info(@PathVariable("id") Long id){
		ChefEntity chef = chefService.getById(id);

        return R.ok().put("chef", chef);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:chef:save")
    public R save(@RequestBody ChefEntity chef){
		chefService.save(chef);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:chef:update")
    public R update(@RequestBody ChefEntity chef){
		chefService.updateById(chef);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:chef:delete")
    public R delete(@RequestBody Long[] ids){
		chefService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
