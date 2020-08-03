package com.atghy.foodmall.common.constant;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-30
 * Description:
 */
public enum CouponConstant {
    VIP0_ENUM("0","0",1.00,"VIP1"),
    VIP1_ENUM("1","1000",0.98,"VIP1"),
    VIP2_ENUM("2","2000",0.93,"VIP2"),
    VIP3_ENUM("3","3000",0.88,"VIP3"),
    VIP4_ENUM("4","4000",0.83,"VIP4"),
    VIP5_ENUM("5","5000",0.78,"VIP5"),
    VIP6_ENUM("6","6000",0.73,"VIP6"),
    POVERTY_STRICKEN_ENUM("8","888888",0.70,"高校贫困生");

    private String code;
    private String msg;
    private Double discount;
    private String score;

    CouponConstant(String code, String score , Double discount , String msg) {
        this.code = code;
        this.msg = msg;
        this.discount = discount;
        this.score = score;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getScore(){
        return score;
    }

    public Double getDiscount(){
        return discount;
    }
}
