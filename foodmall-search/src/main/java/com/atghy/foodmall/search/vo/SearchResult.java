package com.atghy.foodmall.search.vo;

import com.atghy.foodmall.common.to.es.SkuEsModel;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-27
 * Description:
 */
@Data
public class SearchResult {
    //查询到的所有商品的信息
    private List<SkuEsModel> foods;

    //分页信息
    private Integer pageNum;
    private Long total;
    private Integer totalPages;

    //当前查询到的结果 所有涉及到的店面
    private List<RestaurantVo> restanrants;
    //当前查询到的结果 所有涉及到的特性
    private List<NatureVo> natures;
    //当前查询到的结果 所有涉及到的分类
    private List<CatalogVo> catalogs;

//=========================================
    @Data
    public static class CatalogVo{
        private Long catalogId;
        private String catalogName;
    }

    @Data
    public static class RestaurantVo{
        private Long restaurantId;
        private String restaurantName;
        private String restaurantLevel;
    }

    @Data
    public static class NatureVo{
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
    }
}
