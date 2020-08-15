package com.atghy.foodmall.order.constant;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-14
 * Description:
 */
public enum PayWayEnumConstant {
    ALIPAY(1,"支付宝付款"),
    WECHATPAY(1,"微信付款"),
    SCHOOLCARDPAY(1,"校园一卡通"),
    ARRIVEPAY(1,"送达付款");

    private int code;
    private String msg;

    PayWayEnumConstant(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
