package com.atghy.foodmall.food.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集所有单菜品表
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-23 14:14:27
 */
@Data
@TableName("ffs_single")
public class SingleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 单菜品id
	 */
	@TableId
	private Long id;
	/**
	 * 饭店名称 外联饭店
	 */
	private String restaurantName;
	/**
	 * 菜名 外联菜品特性
	 */
	private String name;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 库存剩余
	 */
	private String quantity;
	/**
	 * 锁定库存量
	 */
	private String quantityLock;
	/**
	 * 图片地址
	 */
	private String imgUrl;
	/**
	 * 主展示标题
	 */
	private String title;
	/**
	 * 副展示标题
	 */
	private String subtitle;
	/**
	 * 上架状态
	 */
	private Integer useStatus;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
