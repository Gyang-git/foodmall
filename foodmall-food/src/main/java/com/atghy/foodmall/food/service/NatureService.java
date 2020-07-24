package com.atghy.foodmall.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.food.entity.NatureEntity;

import java.util.Map;

/**
 * 收集菜品特性 包括酸甜苦辣、油炸、口感(软硬)、冷热、鲜品 外联单菜品
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-23 11:48:38
 */
public interface NatureService extends IService<NatureEntity> {

    PageUtils queryPage(Map<String, Object> params);

    NatureEntity getNatureByName(String name);
}

