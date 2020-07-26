package com.atghy.foodmall.search.controller;

import com.atghy.foodmall.common.exception.BizCodeEnume;
import com.atghy.foodmall.common.to.es.SkuEsModel;
import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.search.service.FoodSaveService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-26
 * Description:
 */
@Slf4j
@RequestMapping("/search/save")
@RestController
public class ElasticSaveController {
    @Autowired
    FoodSaveService foodSaveService;

    //上架商品
    @PostMapping("/food")
    public R foodStatusUp(@RequestBody SkuEsModel skuEsModel){
        boolean b =false;
        try {
            b = foodSaveService.foodStatusUp(skuEsModel);
        }catch (Exception e){
            log.error("ElasticSaveController餐品上架异常：{}",e);
            return R.error(BizCodeEnume.FOOD_UP_EXCEPTION.getCode(),BizCodeEnume.FOOD_UP_EXCEPTION.getMsg());
        }

        if (!b){
            return R.ok();
        }else {
            return R.error(BizCodeEnume.FOOD_UP_EXCEPTION.getCode(),BizCodeEnume.FOOD_UP_EXCEPTION.getMsg());
        }
    }
}
