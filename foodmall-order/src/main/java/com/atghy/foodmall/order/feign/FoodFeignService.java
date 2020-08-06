package com.atghy.foodmall.order.feign;

import com.atghy.foodmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
}
