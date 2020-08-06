package com.atghy.foodmall.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-12
 * Description:
 */
public class NoStockException extends RuntimeException{

    @Getter
    @Setter
    private String name;

    public NoStockException(Long name){
        super("餐品--"+name+"--没有足够的库存了");
    }

    public NoStockException(String msg) {
        super(msg);
    }
}
