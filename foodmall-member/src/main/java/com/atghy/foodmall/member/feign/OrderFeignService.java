package com.atghy.foodmall.member.feign;

import com.atghy.foodmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-12
 * Description:
 */
@FeignClient("foodmall-order")
public interface OrderFeignService {
    @PostMapping("/order/order/listWithItem")
    R listWithItem(@RequestBody Map<String,Object> params);
}
