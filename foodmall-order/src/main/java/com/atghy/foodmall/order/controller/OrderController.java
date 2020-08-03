package com.atghy.foodmall.order.controller;

import java.util.Arrays;
import java.util.Map;

import com.atghy.foodmall.order.vo.OrderConfirmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.atghy.foodmall.order.entity.OrderEntity;
import com.atghy.foodmall.order.service.OrderService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.R;



/**
 * 收集订单信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-02 16:29:45
 */
@RestController
@RequestMapping("order/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //去结算
    @GetMapping("toTrace")
    public String toTrace(Model model){
        OrderConfirmVo oderConfirmVo = orderService.confirmOrder();
        model.addAttribute("data",oderConfirmVo);
        return "confirm";
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:order:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:order:info")
    public R info(@PathVariable("id") Long id){
		OrderEntity order = orderService.getById(id);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:order:save")
    public R save(@RequestBody OrderEntity order){
		orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:order:update")
    public R update(@RequestBody OrderEntity order){
		orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:order:delete")
    public R delete(@RequestBody Long[] ids){
		orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
