package com.atghy.foodmall.common.constant;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-06
 * Description:
 */
public enum  OrderStatusEnumConstant {
    ORDER_WAIT_PAY(1,"待付款"),
    ORDER_WAIT_DELIVERY(2,"待派送"),
    ORDER_DELIVING(3,"派送中"),
    ORDER_WAIT_FEEDBACK(4,"待反馈"),
    ORDER_SUCCESS(5,"订单完成"),
    ORDER_CANCEL(6,"订单取消"),
    ORDER_FAIl(7,"订单异常");

    private int code;
    private String msg;

    OrderStatusEnumConstant(int code, String msg) {
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
