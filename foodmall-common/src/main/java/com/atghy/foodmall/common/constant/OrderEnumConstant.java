package com.atghy.foodmall.common.constant;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-05
 * Description:
 */
public enum  OrderEnumConstant {
    ORDER_SUBMITING(1,"订单提交处理中"),
    ORDER_SUCCESS(2,"订单提交成功"),
    ORDER_FAIR_STOCKLACK(3,"订单提交失败 库存不足"),
    ORDER_FAIR_CONECT_TIMEOUT(4,"订单提交失败 系统连接超时"),
    ORDER_FAIR_ORTHER(5,"订单提交失败 详情联系管理员"),
    ORDER_FAIR_PRICE_CHANGE(6,"订单提交失败 餐品价格发生变化");

    private int code;
    private String msg;

    OrderEnumConstant(int code, String msg) {
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
