package com.atghy.foodmall.food.service;

import com.atghy.foodmall.food.vo.SetmealVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.food.entity.SetmealEntity;

import java.util.Map;

/**
 * 收集套餐
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-21 14:09:51
 */
public interface SetmealService extends IService<SetmealEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addSetmeal(SetmealVo setmealVo);

    Boolean upSetmeal(Long id);
}

