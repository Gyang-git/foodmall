package com.atghy.foodmall.food.vo;

import com.atghy.foodmall.food.entity.SetmealEntity;
import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-19
 * Description:
 */
@Data
public class SetmealItemVo {
    //1-封装基本信息
    SetmealEntity single;

    //2-当前餐品的秒杀信息
    SeckillInfoVo seckillInfo;
}
