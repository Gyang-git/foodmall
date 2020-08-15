package com.atghy.foodmall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 收集订单包含餐品
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-06 21:32:15
 */
@TableName("ofs_order_item")
public class OrderItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@Getter
	@Setter
	private Long id;
	/**
	 * 
	 */
	@Getter
	@Setter
	private String orderSn;
	/**
	 * 对应表id
	 */
	@Getter
	@Setter
	private Long orderId;
	/**
	 * 餐品id  v
	 */
	@Getter
	@Setter
	private Long foodId;
	/**
	 * 餐品类型  v
	 */
	@Getter
	@Setter
	private String foodType;

	@Getter
	@Setter
	private String imgUrl;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private BigDecimal price;

	@Getter
	@Setter
	private Integer count;

	@Getter
	@Setter
	private BigDecimal totalPrice;
	/**
	 * 启用状态
	 */
	@Getter
	@Setter
	private Integer status;


}
