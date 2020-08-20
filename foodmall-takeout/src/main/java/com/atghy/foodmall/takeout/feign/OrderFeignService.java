package com.atghy.foodmall.takeout.feign;

import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.takeout.vo.HealthMonitoringVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-18
 * Description:
 */
@FeignClient("foodmall-order")
public interface OrderFeignService {
    @PostMapping("/order/healthmonitoring/back/saveHealthMonitor")
    R saveHealthMonitor(@RequestBody HealthMonitoringVo healthMonitoringVo);

    @RequestMapping("/order/order/back/takeoutOrder/{orderSn}/{takeoutSn}/{healthId}")
    R takeoutOrder(@PathVariable("orderSn") String orderSn, @PathVariable("takeoutSn") String takeoutSn, @PathVariable("healthId") Long healthId);

    @RequestMapping("/order/order/back/takeoutArrive/{orderSn}/{takeSn}")
    R takeoutArrive(@PathVariable("orderSn")String orderSn,@PathVariable("takeSn")String takeSn);
}
