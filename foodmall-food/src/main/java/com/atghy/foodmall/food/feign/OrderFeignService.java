package com.atghy.foodmall.food.feign;

import com.atghy.foodmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-08
 * Description:
 */
@FeignClient("foodmall-order")
public interface OrderFeignService {
    @GetMapping("/order/order/getOrder/{orderSn}")
    R getOrderByOrderSn(@PathVariable("orderSn") String orderSn);
}
