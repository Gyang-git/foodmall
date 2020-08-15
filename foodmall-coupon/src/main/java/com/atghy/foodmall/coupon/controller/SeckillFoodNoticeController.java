package com.atghy.foodmall.coupon.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.coupon.entity.SeckillFoodNoticeEntity;
import com.atghy.foodmall.coupon.service.SeckillFoodNoticeService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 秒杀餐品通知订阅
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-14 20:32:22
 */
@RestController
@RequestMapping("coupon/seckillfoodnotice")
public class SeckillFoodNoticeController {
    @Autowired
    private SeckillFoodNoticeService seckillFoodNoticeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:seckillfoodnotice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillFoodNoticeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:seckillfoodnotice:info")
    public R info(@PathVariable("id") Long id){
		SeckillFoodNoticeEntity seckillFoodNotice = seckillFoodNoticeService.getById(id);

        return R.ok().put("seckillFoodNotice", seckillFoodNotice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:seckillfoodnotice:save")
    public R save(@RequestBody SeckillFoodNoticeEntity seckillFoodNotice){
		seckillFoodNoticeService.save(seckillFoodNotice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:seckillfoodnotice:update")
    public R update(@RequestBody SeckillFoodNoticeEntity seckillFoodNotice){
		seckillFoodNoticeService.updateById(seckillFoodNotice);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:seckillfoodnotice:delete")
    public R delete(@RequestBody Long[] ids){
		seckillFoodNoticeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
