package com.atghy.foodmall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-10
 * Description:
 */
@Data
public class FareInfoVo {
    //服务费 = 包装费用 + 配送费用
    private BigDecimal serviceFare;

    //折扣
    private BigDecimal discount;
}
