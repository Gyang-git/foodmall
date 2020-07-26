package com.atghy.foodmall.food.feign;

import com.atghy.foodmall.common.to.es.SkuEsModel;
import com.atghy.foodmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-26
 * Description:
 */
@FeignClient("foodmall-search")
public interface SearchFeignService {
    //上架商品
    @PostMapping("/search/save/food")
    R foodStatusUp(@RequestBody SkuEsModel skuEsModel);
}
