package com.atghy.foodmall.order.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-05
 * Description:
 */
@Data
public class AddressVo {
    //地址
    /**
     * 地址一级分类
     */
    private String firstAddress;
    /**
     * 地址二级分类
     */
    private String secondAddress;
    /**
     * 地址三级分类
     */
    private String thirdAddress;
}
