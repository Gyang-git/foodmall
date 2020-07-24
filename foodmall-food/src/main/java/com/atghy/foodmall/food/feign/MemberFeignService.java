package com.atghy.foodmall.food.feign;

import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.food.vo.ManagerVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-22
 * Description:
 */
@FeignClient("foodmall-member")
public interface MemberFeignService {
    @GetMapping("/member/agent/getAgentIdByName/{name}")
    Long getAgentIdByName(@PathVariable("name") String name);

    @RequestMapping("/member/manager/save")
    //@RequiresPermissions("member:manager:save")
    R save(@RequestBody ManagerVo manager);
}
