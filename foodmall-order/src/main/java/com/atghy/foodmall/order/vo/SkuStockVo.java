package com.atghy.foodmall.order.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-03
 * Description:
 */
@Data
public class SkuStockVo {
    private Long skuId;
    private Boolean hasStock;
}
