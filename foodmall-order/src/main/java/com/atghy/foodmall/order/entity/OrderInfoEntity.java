package com.atghy.foodmall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 记录订单详情信息 
包括派单信息与订单状态信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-14 18:57:17
 */
@Data
@TableName("ofs_order_info")
public class OrderInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 订单号
	 */
	private String orderSn;
	/**
	 * 详情号
	 */
	private String takeSn;
	/**
	 * 登记时间
	 */
	private Date time;
	/**
	 * 登记信息
	 */
	private String info;
	/**
	 * 所处位置
	 */
	private String address;
	/**
	 * 使用状态
	 */
	private Integer status;

}
