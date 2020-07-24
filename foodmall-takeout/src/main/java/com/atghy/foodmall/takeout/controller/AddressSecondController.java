package com.atghy.foodmall.takeout.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atghy.foodmall.takeout.entity.AddressSecondEntity;
import com.atghy.foodmall.takeout.service.AddressSecondService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 外卖可选地址二级分类
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@RestController
@RequestMapping("takeout/addresssecond")
public class AddressSecondController {
    @Autowired
    private AddressSecondService addressSecondService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("takeout:addresssecond:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = addressSecondService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("takeout:addresssecond:info")
    public R info(@PathVariable("id") Long id){
		AddressSecondEntity addressSecond = addressSecondService.getById(id);

        return R.ok().put("addressSecond", addressSecond);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("takeout:addresssecond:save")
    public R save(@RequestBody AddressSecondEntity addressSecond){
		addressSecondService.save(addressSecond);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("takeout:addresssecond:update")
    public R update(@RequestBody AddressSecondEntity addressSecond){
		addressSecondService.updateById(addressSecond);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("takeout:addresssecond:delete")
    public R delete(@RequestBody Long[] ids){
		addressSecondService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
