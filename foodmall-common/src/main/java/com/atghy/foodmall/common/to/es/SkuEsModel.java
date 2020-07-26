package com.atghy.foodmall.common.to.es;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-25
 * Description:
 */
@Data
public class SkuEsModel {
    private Long isSingle;//--
    private String restaurantName; //v--
    private String name; //v--
    private Long quantity; //v--
    private Boolean hasStock; //库存--
    private Long useStatus;
    private String imgUrl; //v--
    private BigDecimal price; //v--int-BigDecimal
    private String title; //v--
    private String subTitle; //v
    private Long singleId;
    private Long setmealId;//--
    private Nature nature;

    @Data
    public static class Nature{
        private Long salty;
        private Long sour;
        private Long bitter;
        private Long sweet;
        private Long spicy;
        private Long fry;
        private Long taste;
        private Long cool;
        private Long fresh;
        private Long score;
        private String categoryName;
    }
}
