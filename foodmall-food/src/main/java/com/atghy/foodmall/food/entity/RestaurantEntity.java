package com.atghy.foodmall.food.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集饭店信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-20 10:35:33
 */
@Data
@TableName("ffs_restaurant")
public class RestaurantEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 饭店id
	 */
	@TableId
	private Long id;
	/**
	 * 饭店名称
	 */
	private String name;
	/**
	 * 负责人名称 外联餐饮负责人
	 */
	private String managerName;
	/**
	 * 饭店月份评级 1-5->表示星级
	 */
	private Integer level;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
