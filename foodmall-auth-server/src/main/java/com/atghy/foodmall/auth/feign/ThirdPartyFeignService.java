package com.atghy.foodmall.auth.feign;

import com.atghy.foodmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-30
 * Description:
 */
@FeignClient("foodmall-third-party")
public interface ThirdPartyFeignService {
    @GetMapping("/thirdparty/sms/sendcode")
    R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code);
}
