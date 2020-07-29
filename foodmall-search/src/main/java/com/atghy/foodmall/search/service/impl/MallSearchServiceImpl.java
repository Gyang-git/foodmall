package com.atghy.foodmall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.atghy.foodmall.common.to.es.SkuEsModel;
import com.atghy.foodmall.search.config.FoodmallElasticsearchConfig;
import com.atghy.foodmall.search.constants.EsConstant;
import com.atghy.foodmall.search.service.MallSearchService;
import com.atghy.foodmall.search.vo.SearchParam;
import com.atghy.foodmall.search.vo.SearchResult;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-27
 * Description:
 */
@Service
public class MallSearchServiceImpl implements MallSearchService {

    @Autowired
    RestHighLevelClient client;

    @Override
    public SearchResult search(SearchParam param) {
        //1-构建出查询需要的DSL语句 响应结果
        SearchResult result = null;
        //2-准备检索请求
        SearchRequest searchRequest = buildSearchRequest(param);
        //3-开始检索
        try {
            SearchResponse response = client.search(searchRequest, FoodmallElasticsearchConfig.COMMON_OPTIONS);
            //4-分析响应数据并封装
            result = buildSearchResult(response,param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private SearchResult buildSearchResult(SearchResponse response, SearchParam param) {
        SearchResult result = new SearchResult();
        SearchHits hits = response.getHits();
        List<SkuEsModel> esModels = new ArrayList<>();
        if (hits.getHits() != null && hits.getHits().length > 0){
            for (SearchHit hit : hits.getHits()) {
                String sourceAsString = hit.getSourceAsString();
                //将String转为SkuEsModel对象
                SkuEsModel esModel = JSON.parseObject(sourceAsString, SkuEsModel.class);
                esModels.add(esModel);
            }
        }
        //1-返回的查询到的商品
        result.setFoods(esModels);
        //2-当前所有商品涉及到的店面信息
        ParsedStringTerms restaurant_agg = response.getAggregations().get("restaurant_agg");
        List<SearchResult.RestaurantVo> restaurantVos = new ArrayList<>();
        List<? extends Terms.Bucket> buckets = restaurant_agg.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            SearchResult.RestaurantVo restaurantVo = new SearchResult.RestaurantVo();
            //得到店面名称
            String keyAsString = bucket.getKeyAsString();
            restaurantVo.setRestaurantName(keyAsString);
            restaurantVos.add(restaurantVo);
        }
        result.setRestanrants(restaurantVos);
        //3-当前所有商品涉及到的分类信息
        ArrayList<SearchResult.CatalogVo> catalogVos = new ArrayList<>();
        ParsedNested catalog_agg = response.getAggregations().get("catalog_agg");
        ParsedStringTerms category_name_agg = catalog_agg.getAggregations().get("category_Name_agg");
        for (Terms.Bucket bucket : category_name_agg.getBuckets()) {
            SearchResult.CatalogVo catalogVo = new SearchResult.CatalogVo();
            //得到分类
            String keyAsString = bucket.getKeyAsString();
            catalogVo.setCatalogName(keyAsString);
            catalogVos.add(catalogVo);
        }
        result.setCatalogs(catalogVos);
        //4-当前所有商品涉及到的特性信息
        ArrayList<SearchResult.NatureVo> natureVos = new ArrayList<>();
        ParsedNested scoree_agg = response.getAggregations().get("scoree_agg");
        ParsedLongTerms score_agg = scoree_agg.getAggregations().get("score_agg");
        for (Terms.Bucket bucket : score_agg.getBuckets()) {
            SearchResult.NatureVo natureVo = new SearchResult.NatureVo();
            //得到评分
            Long keyAsNumber = (Long) bucket.getKeyAsNumber();
            natureVo.setScore(keyAsNumber);
            natureVos.add(natureVo);
        }
        result.setNatures(natureVos);
//        //5-分页信息-页码
        result.setPageNum(param.getPageNum());
        long total = hits.getTotalHits().value;
        result.setTotal(total);
        int totalPages = (int) (total % EsConstant.FOOD_PAGESIZE == 0 ? (int) total / EsConstant.FOOD_PAGESIZE : (total % EsConstant.FOOD_PAGESIZE + 1));
        result.setTotalPages(totalPages);
        return result;
    }

    /**
     * 准备检索请求
     * 模糊匹配 过滤(按照特性、分类、店面、价格区间、库存) 排序、分页、高亮、聚合分析
     * @param param
     * @return
     */
    private SearchRequest buildSearchRequest(SearchParam param){
        //构建DSL语句
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //1-构建检索池
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //1--must--模糊匹配
        if(!StringUtils.isEmpty(param.getKeyword())){
            boolQuery.must(QueryBuilders.matchQuery("title",param.getKeyword()));
        }
        //1--filter--过滤
        //库存状态
        if (param.getHasStock() != null){
            boolQuery.filter(QueryBuilders.termQuery("hasStock",param.getHasStock() == 1));
        }
        //单品
        if (param.getIsSingle() != null){
            boolQuery.filter(QueryBuilders.termQuery("isSingle",param.getIsSingle()));
        }
        //单品id
        if (param.getSingleId() != null && param.getSingleId().size() > 0){
            boolQuery.filter(QueryBuilders.termQuery("singleId",param.getSingleId()));
        }
        //套餐id
        if (param.getSetmealId() != null && param.getSetmealId().size() > 0){
            boolQuery.filter(QueryBuilders.termQuery("setmealId",param.getSetmealId()));
        }


        //价格区间
        if (!StringUtils.isEmpty(param.getPrice())){
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("price");
            String[] s = param.getPrice().split("_");
            if (s.length == 2){
                //价格区间
                rangeQuery.gte(s[0]).lte(s[1]);
            }else if (s.length == 1){
                if (param.getPrice().startsWith("_")){
                    //>
                    rangeQuery.lte(s[0]);
                }
                if (param.getPrice().endsWith("_")){
                    rangeQuery.gte(s[0]);
                }
            }
            boolQuery.filter(rangeQuery);
        }
        //分类单品
        if (param.getCategoryName() != null){
            BoolQueryBuilder nestedboolQuery = QueryBuilders.boolQuery();
            nestedboolQuery.must(QueryBuilders.termQuery("nature.categoryName",param.getCategoryName()));
            NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("nature", nestedboolQuery, ScoreMode.None);
            boolQuery.filter(nestedQuery);
        }
        //构建完成所有query
        sourceBuilder.query(boolQuery);
        //排序、分页、高亮
        //2.1-排序
        if(!StringUtils.isEmpty(param.getSort())){
            String sort = param.getSort();
            String[] s = sort.split("_");
            SortOrder order = s[1].equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC;
            sourceBuilder.sort(s[0],order);
        }
        //2.2-分页
        sourceBuilder.from((param.getPageNum() - 1)* EsConstant.FOOD_PAGESIZE);
        sourceBuilder.size(EsConstant.FOOD_PAGESIZE);
        //2.3-高亮
        if (!StringUtils.isEmpty(param.getKeyword())){
            HighlightBuilder builder = new HighlightBuilder();
            builder.field("title");
            builder.preTags("<b style = 'color:red'>");
            builder.postTags("</b>");
            sourceBuilder.highlighter(builder);
        }
        //聚合分析
        //1-店面聚合
        TermsAggregationBuilder restaurant_agg = AggregationBuilders.terms("restaurant_agg");
        //指定查询记录个数
        restaurant_agg.field("restaurantName").size(50);
        //加入restaurant_agg聚合
        sourceBuilder.aggregation(restaurant_agg);

        //2-分类聚合
        NestedAggregationBuilder catalog_agg = AggregationBuilders.nested("catalog_agg", "nature");
        //聚合出当前所有的categoryName
        TermsAggregationBuilder category_name_agg = AggregationBuilders.terms("category_Name_agg").field("nature.categoryName");
        catalog_agg.subAggregation(category_name_agg);
        sourceBuilder.aggregation(catalog_agg);

        //2-特性聚合
        NestedAggregationBuilder bitterr_agg = AggregationBuilders.nested("bitterr_agg", "nature");
        TermsAggregationBuilder bitter_agg = AggregationBuilders.terms("bitter_agg").field("nature.bitter");
        bitterr_agg.subAggregation(bitter_agg);
        sourceBuilder.aggregation(bitterr_agg);

        //2-特性聚合
        NestedAggregationBuilder saltyy_agg = AggregationBuilders.nested("saltyy_agg", "nature");
        TermsAggregationBuilder salty_agg = AggregationBuilders.terms("salty_agg").field("nature.salty");
        saltyy_agg.subAggregation(salty_agg);
        sourceBuilder.aggregation(saltyy_agg);

        //2-特性聚合
        NestedAggregationBuilder sourr_agg = AggregationBuilders.nested("sourr_agg", "nature");
        TermsAggregationBuilder sour_agg = AggregationBuilders.terms("sour_agg").field("nature.sour");
        sourr_agg.subAggregation(sour_agg);
        sourceBuilder.aggregation(sourr_agg);

        //2-特性聚合
        NestedAggregationBuilder sweett_agg = AggregationBuilders.nested("sweett_agg", "nature");
        TermsAggregationBuilder sweet_agg = AggregationBuilders.terms("sweet_agg").field("nature.sweet");
        sweett_agg.subAggregation(sweet_agg);
        sourceBuilder.aggregation(sweett_agg);

        //2-特性聚合
        NestedAggregationBuilder spicyy_agg = AggregationBuilders.nested("spicyy_agg", "nature");
        TermsAggregationBuilder spicy_agg = AggregationBuilders.terms("spicy_agg").field("nature.spicy");
        spicyy_agg.subAggregation(spicy_agg);
        sourceBuilder.aggregation(spicyy_agg);

        //2-特性聚合
        NestedAggregationBuilder cooll_agg = AggregationBuilders.nested("cooll_agg", "nature");
        TermsAggregationBuilder cool_agg = AggregationBuilders.terms("cool_agg").field("nature.cool");
        cooll_agg.subAggregation(cool_agg);
        sourceBuilder.aggregation(cooll_agg);

        //2-特性聚合
        NestedAggregationBuilder freshh_agg = AggregationBuilders.nested("freshh_agg", "nature");
        TermsAggregationBuilder fresh_agg = AggregationBuilders.terms("fresh_agg").field("nature.fresh");
        freshh_agg.subAggregation(fresh_agg);
        sourceBuilder.aggregation(freshh_agg);

        //2-特性聚合
        NestedAggregationBuilder fryy_agg = AggregationBuilders.nested("fryy_agg", "nature");
        TermsAggregationBuilder fry_agg = AggregationBuilders.terms("fry_agg").field("nature.fry");
        fryy_agg.subAggregation(fry_agg);
        sourceBuilder.aggregation(fryy_agg);

        //2-特性聚合
        NestedAggregationBuilder scoree_agg = AggregationBuilders.nested("scoree_agg", "nature");
        TermsAggregationBuilder score_agg = AggregationBuilders.terms("score_agg").field("nature.score");
        scoree_agg.subAggregation(score_agg);
        sourceBuilder.aggregation(scoree_agg);

        String s = sourceBuilder.toString();
        System.out.println("构建的DSL语句" + s);
        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.FOOD_INDEX}, sourceBuilder);
        return searchRequest;
    }
}
