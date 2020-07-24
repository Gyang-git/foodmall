package com.atghy.foodmall.food.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集饭店与供应商的关联信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
@Data
@TableName("ffs_restaurant_agent")
public class RestaurantAgentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 饭店id
	 */
	private Long restaurantId;
	/**
	 * 供应商id
	 */
	private Long agentId;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
