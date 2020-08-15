package com.atghy.foodmall.coupon.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.coupon.entity.SeckillFoodRelationEntity;
import com.atghy.foodmall.coupon.service.SeckillFoodRelationService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 秒杀活动餐品关联
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-14 20:32:22
 */
@RestController
@RequestMapping("coupon/seckillfoodrelation")
public class SeckillFoodRelationController {
    @Autowired
    private SeckillFoodRelationService seckillFoodRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:seckillfoodrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillFoodRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:seckillfoodrelation:info")
    public R info(@PathVariable("id") Long id){
		SeckillFoodRelationEntity seckillFoodRelation = seckillFoodRelationService.getById(id);

        return R.ok().put("seckillFoodRelation", seckillFoodRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:seckillfoodrelation:save")
    public R save(@RequestBody SeckillFoodRelationEntity seckillFoodRelation){
		seckillFoodRelationService.save(seckillFoodRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:seckillfoodrelation:update")
    public R update(@RequestBody SeckillFoodRelationEntity seckillFoodRelation){
		seckillFoodRelationService.updateById(seckillFoodRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:seckillfoodrelation:delete")
    public R delete(@RequestBody Long[] ids){
		seckillFoodRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
