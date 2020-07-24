package com.atghy.foodmall.member.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atghy.foodmall.member.entity.CustomerEntity;
import com.atghy.foodmall.member.service.CustomerService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集顾客个人基础信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-19 10:32:49
 */
@RestController
@RequestMapping("member/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:customer:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = customerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:customer:info")
    public R info(@PathVariable("id") Long id){
		CustomerEntity customer = customerService.getById(id);

        return R.ok().put("customer", customer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:customer:save")
    public R save(@RequestBody CustomerEntity customer){
		customerService.save(customer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:customer:update")
    public R update(@RequestBody CustomerEntity customer){
		customerService.updateById(customer);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:customer:delete")
    public R delete(@RequestBody Long[] ids){
		customerService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    //删除单个
    @PostMapping("/deleteById")
    public R deleteById(@RequestBody Long id){
        customerService.removeById(id);
        return R.ok();
    }

}
