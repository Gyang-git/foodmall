package com.atghy.foodmall.member.feign;

import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.member.vo.UserScoreInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-30
 * Description:
 */
@FeignClient("foodmall-coupon")
public interface CouponFeignService {
    @RequestMapping("/coupon/userscoreinfo/save")
    R save(@RequestBody UserScoreInfoVo userScoreInfo);
}
