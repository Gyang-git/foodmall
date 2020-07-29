package com.atghy.foodmall.ware.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.ware.entity.WareOutInfoEntity;
import com.atghy.foodmall.ware.service.WareOutInfoService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集仓库原料出货明细
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-25 10:38:18
 */
@RestController
@RequestMapping("ware/wareoutinfo")
public class WareOutInfoController {
    @Autowired
    private WareOutInfoService wareOutInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ware:wareoutinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wareOutInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ware:wareoutinfo:info")
    public R info(@PathVariable("id") Long id){
		WareOutInfoEntity wareOutInfo = wareOutInfoService.getById(id);

        return R.ok().put("wareOutInfo", wareOutInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:wareoutinfo:save")
    public R save(@RequestBody WareOutInfoEntity wareOutInfo){
		wareOutInfoService.save(wareOutInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:wareoutinfo:update")
    public R update(@RequestBody WareOutInfoEntity wareOutInfo){
		wareOutInfoService.updateById(wareOutInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:wareoutinfo:delete")
    public R delete(@RequestBody Long[] ids){
		wareOutInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
