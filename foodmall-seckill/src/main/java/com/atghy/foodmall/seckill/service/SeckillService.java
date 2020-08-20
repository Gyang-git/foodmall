package com.atghy.foodmall.seckill.service;

import com.atghy.foodmall.seckill.to.SeckillFoodRedisTo;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-14
 * Description:
 */
public interface SeckillService {
    List<SeckillFoodRedisTo> getCurrentSeckillFoods();

    SeckillFoodRedisTo getFoodSeckillInfo(Long foodId);

    String kill(String killId, String key, Integer num);

    void uploadSeckillFoodLates3Days();
}
