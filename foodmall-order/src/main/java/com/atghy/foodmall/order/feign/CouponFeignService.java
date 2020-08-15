package com.atghy.foodmall.order.feign;

import com.atghy.foodmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-07
 * Description:
 */
@FeignClient("foodmall-coupon")
public interface CouponFeignService {
    @GetMapping("/coupon/userscoreinfo/getDiscount")
    R getDiscountByUuid(@RequestParam Long uuid);
}
