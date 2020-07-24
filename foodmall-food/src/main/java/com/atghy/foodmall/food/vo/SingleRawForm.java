package com.atghy.foodmall.food.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-22
 * Description:
 */
@Data
public class SingleRawForm {
    private String rawName;
    private Integer quantity;
    private String agentName;
    private Integer status;
}
