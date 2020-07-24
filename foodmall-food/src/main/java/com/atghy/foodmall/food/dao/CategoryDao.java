package com.atghy.foodmall.food.dao;

import com.atghy.foodmall.food.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收集菜品分类 包括美食、水果、甜品饮品、汉堡、披萨、买菜、鲁菜、川菜、粤菜、淮扬菜
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
