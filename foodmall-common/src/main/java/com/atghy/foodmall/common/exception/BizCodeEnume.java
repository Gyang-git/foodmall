package com.atghy.foodmall.common.exception;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-05-31
 * Description:错误码和错误信息定义类
 */

/**
 * 1-错误码定义为5位数字
 * 2-前两位表示业务场景，最后三位表示错误码。例如100001/10：通用 001：系统未知异常
 * 3-维护错误码后需要维护错误描述，将他们定义位枚举形式
 * 错误码列表
 *  10：通用
 *      001：参数格式校验
 *      002：短信验证码频率太高
 *  11：餐品
 *  12：订单
 *  13：购物车
 *  14：外卖
 *  15: 用户
 *  21：库存
 */
public enum BizCodeEnume {
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VAILD_EXCEPTION(10001,"参数格式校验失败"),
    TOO_MANY_REQUEST(10002,"请求流量过大"),
    SMS_CODE_EXCEPTION(10001,"验证码获取频率太高 请稍后重试"),
    FOOD_UP_EXCEPTION(11000,"餐品上架异常"),
    RESTAURANT_LEVEL_TOOLOW_EXCEPTION(11002,"店面等级过低 无法上架餐品"),
    USER_EXIST_EXCEPTION(15001,"用户已存在"),
    PHONE_EXIST_EXCEPTION(15002,"手机号已存在"),
    MANAGER_PERMIT_LACK_EXCEPTION(15004,"店面负责人证件缺失"),
    NO_STOCK_EXCEPTION(21000,"商品库存不足"),
    LOGINACCT_PASSWORD_INNAILD_EXCEPTION(15003,"账号密码错误");

    private int code;
    private String msg;
    BizCodeEnume(int code, String msg) {
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
