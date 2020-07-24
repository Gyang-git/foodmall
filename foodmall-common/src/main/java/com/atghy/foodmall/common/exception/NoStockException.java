package com.atghy.foodmall.common.exception;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-12
 * Description:
 */
public class NoStockException extends RuntimeException{
    private Long skuId;
    public NoStockException(Long skuId){
        super("商品id--"+skuId+"--没有足够的库存了");
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
}
