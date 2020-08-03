package com.atghy.foodmall.coupon.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.coupon.entity.LevelStandardEntity;
import com.atghy.foodmall.coupon.service.LevelStandardService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集用户优惠标准
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-30 12:30:16
 */
@RestController
@RequestMapping("coupon/levelstandard")
public class LevelStandardController {
    @Autowired
    private LevelStandardService levelStandardService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:levelstandard:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = levelStandardService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:levelstandard:info")
    public R info(@PathVariable("id") Long id){
		LevelStandardEntity levelStandard = levelStandardService.getById(id);

        return R.ok().put("levelStandard", levelStandard);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:levelstandard:save")
    public R save(@RequestBody LevelStandardEntity levelStandard){
		levelStandardService.save(levelStandard);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:levelstandard:update")
    public R update(@RequestBody LevelStandardEntity levelStandard){
		levelStandardService.updateById(levelStandard);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:levelstandard:delete")
    public R delete(@RequestBody Long[] ids){
		levelStandardService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
