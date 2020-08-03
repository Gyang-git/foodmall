package com.atghy.foodmall.auth.feign;

import com.atghy.foodmall.auth.vo.LoginVo;
import com.atghy.foodmall.auth.vo.RegisterVo;
import com.atghy.foodmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-30
 * Description:
 */
@FeignClient("foodmall-member")
public interface MemberFeignService {
    @PostMapping("/member/customer/regist")
    R regist(@RequestBody RegisterVo vo);

    @PostMapping("/member/customer/login")
    R login(@RequestBody LoginVo vo);
}
