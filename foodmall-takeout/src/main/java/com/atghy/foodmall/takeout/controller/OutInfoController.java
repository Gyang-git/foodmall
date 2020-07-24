package com.atghy.foodmall.takeout.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.takeout.entity.OutInfoEntity;
import com.atghy.foodmall.takeout.service.OutInfoService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 记录外卖派送信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@RestController
@RequestMapping("takeout/outinfo")
public class OutInfoController {
    @Autowired
    private OutInfoService outInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("takeout:outinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = outInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("takeout:outinfo:info")
    public R info(@PathVariable("id") Long id){
		OutInfoEntity outInfo = outInfoService.getById(id);

        return R.ok().put("outInfo", outInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("takeout:outinfo:save")
    public R save(@RequestBody OutInfoEntity outInfo){
		outInfoService.save(outInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("takeout:outinfo:update")
    public R update(@RequestBody OutInfoEntity outInfo){
		outInfoService.updateById(outInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("takeout:outinfo:delete")
    public R delete(@RequestBody Long[] ids){
		outInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
