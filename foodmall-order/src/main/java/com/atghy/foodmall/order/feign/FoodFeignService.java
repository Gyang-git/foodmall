package com.atghy.foodmall.order.feign;

import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.order.vo.WareSkuLockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-03
 * Description:
 */
@FeignClient("foodmall-food")
public interface FoodFeignService {
    @PostMapping("/food/single/hasStock")
    R getSingleHasStock(@RequestBody List<Long> singleIds);

    @RequestMapping("/food/single/info/{id}")
    R info(@PathVariable("id") Long id);

    @GetMapping("/food/single/getSingleByName/{name}")
    R getSingleByName(@PathVariable("name")String name);

    @PostMapping("/food/Stock/lock/order")
    R orderLockStock(@RequestBody WareSkuLockVo lockVo);
}
