package com.atghy.foodmall.takeout.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-17
 * Description: 订单配送保存vo
 */
@Data
public class OrderTakeoutVo {
    private String orderSn;

    private String vehicle;

    private String chefName;

    private String packorName;

    private String takeoutName;
}
