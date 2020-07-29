package com.atghy.foodmall.food.vo;

import com.atghy.foodmall.food.entity.NatureEntity;
import com.atghy.foodmall.food.entity.SingleEntity;
import com.atghy.foodmall.food.entity.SingleRawEntity;
import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-29
 * Description: 返回餐品详细信息VO
 */
@Data
public class SingleItemVo {
    //1-封装基本信息
    SingleEntity single;
    //2-封装特性组合
    NatureEntity natureEntity;
    //3-封装原料信息
    SingleRawEntity singleRawEntity;
    //2-当前餐品的秒杀信息
    SeckillInfoVo seckillInfo;
}
