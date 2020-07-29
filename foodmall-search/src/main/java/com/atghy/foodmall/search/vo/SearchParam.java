package com.atghy.foodmall.search.vo;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-25
 * Description:
 */
@Data
public class SearchParam {
    //检索关键字
    private String keyword;
    //检索是否为单品
    private Long isSingle;
    //检索分类名
    private String categoryName;
    //排序条件
    private String sort;
    //单品id
    private List<Long> singleId;
    //套餐id
    private List<Long> setmealId;

    //过滤条件
    //是否只显示有货
    private Integer hasStock;
    //价格区间查询
    private String price;
    //按照店面进行查询 支持多选
    private List<Long> restaurantId;
    //按照店面等级进行查询 支持多选
    private List<Long> restaurantLevel;
    //按照餐品特性进行筛选 支持多选
    private List<String> nature;
    //页码
    private Integer pageNum;
}
