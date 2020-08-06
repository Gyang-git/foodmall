package com.atghy.foodmall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-05
 * Description: 订单提交的数据
 */
@Data
public class OrderSubmitVo {
    //地址
    private AddressVo addressVo;

    //支付方式
    private Integer payType;

    //应付价格
    private BigDecimal payPrice;

    //订单备注
    private String note;

    //订单防重令牌
    private String orderToken;

    //提交餐品 无需提交 再次查询即可
}
