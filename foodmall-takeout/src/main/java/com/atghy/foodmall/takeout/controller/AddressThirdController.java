package com.atghy.foodmall.takeout.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.takeout.entity.AddressThirdEntity;
import com.atghy.foodmall.takeout.service.AddressThirdService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 外卖可选地址三级分类
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@RestController
@RequestMapping("takeout/addressthird")
public class AddressThirdController {
    @Autowired
    private AddressThirdService addressThirdService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("takeout:addressthird:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = addressThirdService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("takeout:addressthird:info")
    public R info(@PathVariable("id") Long id){
		AddressThirdEntity addressThird = addressThirdService.getById(id);

        return R.ok().put("addressThird", addressThird);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("takeout:addressthird:save")
    public R save(@RequestBody AddressThirdEntity addressThird){
		addressThirdService.save(addressThird);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("takeout:addressthird:update")
    public R update(@RequestBody AddressThirdEntity addressThird){
		addressThirdService.updateById(addressThird);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("takeout:addressthird:delete")
    public R delete(@RequestBody Long[] ids){
		addressThirdService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
