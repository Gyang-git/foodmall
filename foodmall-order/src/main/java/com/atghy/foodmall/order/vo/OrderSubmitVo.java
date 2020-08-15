package com.atghy.foodmall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-05
 * Description: 订单页面提交过来的数据
 */
@Data
public class OrderSubmitVo {
    //地址
    private String firstAddress;    //v

    private String secondAddress;   //v

    private String thirdAddress;    //v

    //支付方式
    private Integer payType;    //v

    //应付价格
    private BigDecimal payPrice;    //v

    //订单备注
    private String note;    //v

    //订单防重令牌
    private String orderToken;  //v

    //提交餐品 无需提交 再次查询即可
}
