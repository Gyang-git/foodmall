package com.atghy.foodmall.order.feign;

import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.order.vo.OrderItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-03
 * Description:
 */
@FeignClient("foodmall-cart")
public interface CartFeignService {
    @GetMapping("/currentUserCartItems")
    @ResponseBody
    List<OrderItemVo> getCurrentUserCartItems();
}
