package com.atghy.foodmall.ware.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.ware.entity.WareInInfoEntity;
import com.atghy.foodmall.ware.service.WareInInfoService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 记录向供应商采购的信息表
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-25 10:38:18
 */
@RestController
@RequestMapping("ware/wareininfo")
public class WareInInfoController {
    @Autowired
    private WareInInfoService wareInInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ware:wareininfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wareInInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{rawId}")
    //@RequiresPermissions("ware:wareininfo:info")
    public R info(@PathVariable("rawId") Long rawId){
		WareInInfoEntity wareInInfo = wareInInfoService.getById(rawId);

        return R.ok().put("wareInInfo", wareInInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:wareininfo:save")
    public R save(@RequestBody WareInInfoEntity wareInInfo){
		wareInInfoService.save(wareInInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:wareininfo:update")
    public R update(@RequestBody WareInInfoEntity wareInInfo){
		wareInInfoService.updateById(wareInInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:wareininfo:delete")
    public R delete(@RequestBody Long[] rawIds){
		wareInInfoService.removeByIds(Arrays.asList(rawIds));

        return R.ok();
    }

}
