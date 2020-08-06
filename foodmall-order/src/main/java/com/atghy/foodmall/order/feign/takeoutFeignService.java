package com.atghy.foodmall.order.feign;

import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.order.vo.AddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-05
 * Description:
 */
@FeignClient("foodmall-takeout")
public interface takeoutFeignService {
    @GetMapping("/takeout/addressthird/getAddress")
    List<AddressVo> getAddress();
}
