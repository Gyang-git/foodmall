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
    private Long isSingle;//
    private String restaurantName;//
    private String name;//
    private Long quantity;
    private Boolean hasStock;//
    private Long useStatus;
    private String imgUrl;
    private BigDecimal price;//
    private String title;//
    private String subTitle;
    private Long singleId;//
    private Long setmealId;//
    private Nature nature;

    @Data
    public static class Nature{//
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
