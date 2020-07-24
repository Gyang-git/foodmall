package com.atghy.foodmall.food.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集套餐
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-21 14:09:51
 */
@Data
@TableName("ffs_setmeal")
public class SetmealEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 饭店名称 外联饭店
	 */
	private String restaurantName;
	/**
	 * 套餐名称
	 */
	private String name;
	/**
	 * 库存剩余
	 */
	private String quantity;
	/**
	 * 锁定库存
	 */
	private String quantityLock;
	/**
	 * 图片地址
	 */
	private String imgUrl;
	/**
	 * 上架状态
	 */
	private Integer useStatus;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 主展示标题
	 */
	private String title;
	/**
	 * 副展示标题
	 */
	private String subtitle;
	/**
	 * 描述
	 */
	@TableField(exist = false)
	private String desc;

	private Integer score;
}
