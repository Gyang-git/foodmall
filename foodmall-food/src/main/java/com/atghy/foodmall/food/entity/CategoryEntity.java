package com.atghy.foodmall.food.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集菜品分类 包括美食、水果、甜品饮品、汉堡、披萨、买菜、鲁菜、川菜、粤菜、淮扬菜
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
@Data
@TableName("ffs_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	@TableId
	private Long id;
	/**
	 * 分类名称
	 */
	private String categoryName;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
