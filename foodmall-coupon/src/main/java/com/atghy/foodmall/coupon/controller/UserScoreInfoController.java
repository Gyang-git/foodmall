package com.atghy.foodmall.coupon.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.coupon.entity.UserScoreInfoEntity;
import com.atghy.foodmall.coupon.service.UserScoreInfoService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集顾客积分情况
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-30 12:30:16
 */
@RestController
@RequestMapping("coupon/userscoreinfo")
public class UserScoreInfoController {
    @Autowired
    private UserScoreInfoService userScoreInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:userscoreinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userScoreInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:userscoreinfo:info")
    public R info(@PathVariable("id") Long id){
		UserScoreInfoEntity userScoreInfo = userScoreInfoService.getById(id);

        return R.ok().put("userScoreInfo", userScoreInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:userscoreinfo:save")
    public R save(@RequestBody UserScoreInfoEntity userScoreInfo){
		userScoreInfoService.save(userScoreInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:userscoreinfo:update")
    public R update(@RequestBody UserScoreInfoEntity userScoreInfo){
		userScoreInfoService.updateById(userScoreInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:userscoreinfo:delete")
    public R delete(@RequestBody Long[] ids){
		userScoreInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
