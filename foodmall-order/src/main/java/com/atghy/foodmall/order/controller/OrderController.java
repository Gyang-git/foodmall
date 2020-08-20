package com.atghy.foodmall.order.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.atghy.foodmall.order.entity.OrderInfoEntity;
import com.atghy.foodmall.order.vo.FareInfoVo;
import com.atghy.foodmall.order.vo.OrderConfirmVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
@RequestMapping("order/order/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/back/takeoutArrive/{orderSn}/{takeSn}")
    public R takeoutArrive(@PathVariable("orderSn")String orderSn,@PathVariable("takeSn")String takeSn){
        boolean b = orderService.takeoutArrive(orderSn,takeSn);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 更新订单为配送状态
     * @param orderSn
     * @return
     */
    @RequestMapping("/back/takeoutOrder/{orderSn}/{takeoutSn}/{healthId}")
    public R takeoutOrder(@PathVariable("orderSn") String orderSn,@PathVariable("takeoutSn") String takeoutSn,@PathVariable("healthId") Long healthId){
        Boolean b = orderService.takeoutOrder(orderSn,takeoutSn,healthId);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/listWithItem")
    public R listWithItem(@RequestBody Map<String,Object> params){
        PageUtils page = orderService.queryPageWithItem(params);
        return R.ok().put("page",page);
    }

    /**
     * 获取运费 服务费 折扣价
     * @return
     */
    @GetMapping("/getOrderInfo")
    public R getOrderInfo(@RequestParam("countTotal") Long countTotal){
        FareInfoVo fareInfoVo = orderService.getOrderInfo(countTotal);
        return R.ok().setData(fareInfoVo);
    }

    @GetMapping("/getOrder/{orderSn}")
    public R getOrderByOrderSn(@PathVariable("orderSn") String orderSn){
        OrderEntity orderEntity = orderService.getOne(new QueryWrapper<OrderEntity>().eq("order_sn", orderSn));
        return R.ok().put("order",orderEntity);
    }

    @RequestMapping("/back/list")
    //@RequiresPermissions("order:order:list")
    public R backList(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);
        return R.ok().put("page", page);
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
