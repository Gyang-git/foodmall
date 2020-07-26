package com.atghy.foodmall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.atghy.foodmall.common.to.es.SkuEsModel;
import com.atghy.foodmall.search.config.FoodmallElasticsearchConfig;
import com.atghy.foodmall.search.constants.EsConstant;
import com.atghy.foodmall.search.service.FoodSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-26
 * Description:
 */
@Slf4j
@Service
public class FoodSaveServiceImpl implements FoodSaveService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public boolean foodStatusUp(SkuEsModel skuEsModel) throws IOException {
        //将数据保存到es
        BulkRequest bulkRequest = new BulkRequest();
        String s = JSON.toJSONString(skuEsModel);
        IndexRequest indexRequest = new IndexRequest(EsConstant.FOOD_INDEX);
        if(skuEsModel.getIsSingle() == 1){
            indexRequest.id(skuEsModel.getSingleId().toString());
        }else {
            indexRequest.id(skuEsModel.getSetmealId().toString());
        }
        indexRequest.source(s,XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index);
        return true;
    }
}
