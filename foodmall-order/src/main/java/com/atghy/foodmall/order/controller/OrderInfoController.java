package com.atghy.foodmall.order.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atghy.foodmall.order.entity.OrderInfoEntity;
import com.atghy.foodmall.order.service.OrderInfoService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 记录订单详情信息 
包括派单信息与订单状态信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-14 18:57:17
 */
@RestController
@RequestMapping("order/orderinfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @GetMapping("/back/getInfo/{orderSn}")
    public R getInfo(@PathVariable("orderSn") String orderSn){
        List<OrderInfoEntity> entities = orderInfoService.getInfo(orderSn);
        return R.ok().put("entities",entities);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:orderinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:orderinfo:info")
    public R info(@PathVariable("id") Long id){
		OrderInfoEntity orderInfo = orderInfoService.getById(id);

        return R.ok().put("orderInfo", orderInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:orderinfo:save")
    public R save(@RequestBody OrderInfoEntity orderInfo){
		orderInfoService.save(orderInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:orderinfo:update")
    public R update(@RequestBody OrderInfoEntity orderInfo){
		orderInfoService.updateById(orderInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:orderinfo:delete")
    public R delete(@RequestBody Long[] ids){
		orderInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
