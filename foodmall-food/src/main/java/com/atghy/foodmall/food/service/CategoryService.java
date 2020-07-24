package com.atghy.foodmall.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.food.entity.CategoryEntity;

import java.util.Map;

/**
 * 收集菜品分类 包括美食、水果、甜品饮品、汉堡、披萨、买菜、鲁菜、川菜、粤菜、淮扬菜
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

