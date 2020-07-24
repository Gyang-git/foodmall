package com.atghy.foodmall.food.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集菜品特性 包括酸甜苦辣、油炸、口感(软硬)、冷热、鲜品 外联单菜品
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-23 11:48:38
 */
@Data
@TableName("ffs_nature")
public class NatureEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 单菜品名称 外联单菜品
	 */
	private String singleName;
	/**
	 * 
	 */
	private String salty;
	/**
	 * 酸 1-5->表示程度
	 */
	private String sour;
	/**
	 * 苦 1-5->表示程度
	 */
	private String bitter;
	/**
	 * 甜 1-5->表示程度
	 */
	private String sweet;
	/**
	 * 辣 1-5->表示程度
	 */
	private String spicy;
	/**
	 * 油炸 1-5->表示程度
	 */
	private String fry;
	/**
	 * 软硬 1-5->表示程度
	 */
	private String taste;
	/**
	 * 冷热 1-5->表示程度
	 */
	private String cool;
	/**
	 * 鲜品 0->非鲜品；1->鲜品
	 */
	private String fresh;
	/**
	 * 当前评分
	 */
	private String score;
	/**
	 * 菜品分类 外联菜品主分类
	 */
	private String categoryName;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
