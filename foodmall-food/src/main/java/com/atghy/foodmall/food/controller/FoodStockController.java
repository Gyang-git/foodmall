package com.atghy.foodmall.food.controller;

import com.atghy.foodmall.common.exception.BizCodeEnume;
import com.atghy.foodmall.common.exception.NoStockException;
import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.food.service.FoodStockService;
import com.atghy.foodmall.food.vo.WareSkuLockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-07
 * Description:
 */
@RestController
@RequestMapping("food/Stock")
public class FoodStockController {

    @Autowired
    private FoodStockService foodStockService;

    /**
     * 锁定库存
     * @param lockVo
     * @return
     */
    @PostMapping("/lock/order")
    public R orderLockStock(@RequestBody WareSkuLockVo lockVo){
        System.out.println("来啦老弟");
        try {
            Boolean lockStock = foodStockService.orderLockStock(lockVo);
            return R.ok();
        } catch (NoStockException e) {
            e.printStackTrace();
            return R.error(BizCodeEnume.NO_STOCK_EXCEPTION.getCode(),BizCodeEnume.NO_STOCK_EXCEPTION.getMsg());
        }
    }
}
