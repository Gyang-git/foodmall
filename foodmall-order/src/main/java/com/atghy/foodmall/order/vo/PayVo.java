package com.atghy.foodmall.order.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-12
 * Description: 支付宝相关数据
 */
@Data
public class PayVo {
    //商户订单号
    private String out_trade_no;
    //订单名称
    private String subject;
    //付款金额
    private String total_amount;
    //商品描述
    private String body;
}
