package com.atghy.foodmall.cart.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-31
 * Description:
 */
@Data
public class UserInfoVo {
    private Long uuid;
    private String userKey;
    //是否为临时用户
    private Boolean tempUser = false;
}
