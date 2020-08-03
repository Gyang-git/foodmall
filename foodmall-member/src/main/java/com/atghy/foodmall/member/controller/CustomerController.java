package com.atghy.foodmall.member.controller;

import java.util.Arrays;
import java.util.Map;


import com.atghy.foodmall.common.exception.BizCodeEnume;
import com.atghy.foodmall.member.Exception.PhoneExistException;
import com.atghy.foodmall.member.Exception.UsernameExistException;
import com.atghy.foodmall.member.vo.CustomerLoginVo;
import com.atghy.foodmall.member.vo.CustomerRegistVo;
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

    @PostMapping("/login")
    public R login(@RequestBody CustomerLoginVo vo){
        CustomerEntity entity = customerService.login(vo);
        if (entity != null){
            return R.ok().setData(entity);
        }else {
            return R.error(BizCodeEnume.LOGINACCT_PASSWORD_INNAILD_EXCEPTION.getCode(),BizCodeEnume.LOGINACCT_PASSWORD_INNAILD_EXCEPTION.getMsg());
        }
    }

    @PostMapping("/regist")
    public R regist(@RequestBody CustomerRegistVo vo){
        try {
            customerService.regist(vo);
        } catch (PhoneExistException e) {
            return R.error(BizCodeEnume.PHONE_EXIST_EXCEPTION.getCode(),BizCodeEnume.PHONE_EXIST_EXCEPTION.getMsg());
        } catch (UsernameExistException e){
            return R.error(BizCodeEnume.USER_EXIST_EXCEPTION.getCode(),BizCodeEnume.USER_EXIST_EXCEPTION.getMsg());
        }
        return R.ok();
    }

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
