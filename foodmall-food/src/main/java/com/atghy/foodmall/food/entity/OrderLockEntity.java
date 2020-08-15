package com.atghy.foodmall.food.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 库存锁定数量
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-08 20:11:32
 */
@Data
@TableName("ffs_order_lock")
public class OrderLockEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String orderSn;
	/**
	 * 
	 */
	private Long skuId;
	/**
	 * 
	 */
	private String type;

	private String name;
	/**
	 * 
	 */
	private Integer lockCount;
	/**
	 * 锁定状态
	 */
	private Integer lockStatus;

	private Date datetime;

	@TableLogic
	private Integer status;

}
