package com.atghy.foodmall.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.food.entity.SingleRawEntity;

import java.util.Map;

/**
 * 收集单菜品制作所需原料
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-22 16:53:54
 */
public interface SingleRawService extends IService<SingleRawEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

