package com.atghy.foodmall.food.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集套餐中单菜品的信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
@Data
@TableName("ffs_single_setmeal")
public class SingleSetmealEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 单菜品id 外联单菜品
	 */
	private Long singleId;
	/**
	 * 套餐名称 外联套餐id
	 */
	private Long setmealId;
	/**
	 *  启用状态
	 */
	private Integer status;

}
