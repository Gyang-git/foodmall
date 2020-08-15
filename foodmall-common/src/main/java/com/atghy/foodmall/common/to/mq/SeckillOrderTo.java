package com.atghy.foodmall.common.to.mq;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-14
 * Description:
 */
@Data
public class SeckillOrderTo {
    private String orderSn;

    private Long promotionSessionId;

    private Long food_id;

    private BigDecimal seckillPrice;

    private Integer num;

    private Long uuid;
}
