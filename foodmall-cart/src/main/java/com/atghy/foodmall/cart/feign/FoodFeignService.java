package com.atghy.foodmall.cart.feign;

import com.atghy.foodmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-31
 * Description:
 */
@FeignClient("foodmall-food")
public interface FoodFeignService {
    @RequestMapping("/food/setmeal/info/{id}")
    //@RequiresPermissions("food:setmeal:info")
    R setmealInfo(@PathVariable("id") Long id);

    @RequestMapping("/food/single/info/{id}")
    //@RequiresPermissions("food:single:info")
    R singleInfo(@PathVariable("id") Long id);

    @GetMapping("/food/single/getSingleByName/{name}")
    R getSingleByName(@PathVariable("name")String name);
}
