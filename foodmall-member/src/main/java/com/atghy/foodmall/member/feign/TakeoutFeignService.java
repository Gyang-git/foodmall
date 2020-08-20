package com.atghy.foodmall.member.feign;

import com.atghy.foodmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-17
 * Description:
 */
@FeignClient("foodmall-takeout")
public interface TakeoutFeignService {
    @GetMapping("/takeout/vehicle/back/getUnUseVehicle")
    R getUnUseVehicle();
}
