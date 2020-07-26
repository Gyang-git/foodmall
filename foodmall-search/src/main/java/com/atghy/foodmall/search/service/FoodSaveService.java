package com.atghy.foodmall.search.service;

import com.atghy.foodmall.common.to.es.SkuEsModel;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-26
 * Description:
 */
public interface FoodSaveService {
    boolean foodStatusUp(SkuEsModel skuEsModel) throws IOException;
}
