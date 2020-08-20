package com.atghy.foodmall.seckill.feign;

import com.atghy.foodmall.common.utils.R;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-19
 * Description:
 */
@FeignClient("foodmall-food")
public interface FoodFeignService {
    @RequestMapping("/food/setmeal/info/{id}")
    //@RequiresPermissions("food:setmeal:info")
    R info(@PathVariable("id") Long id);
}
