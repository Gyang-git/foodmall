package com.atghy.foodmall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集订单退货情况
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-02 16:29:45
 */
@Data
@TableName("ofs_refund_info")
public class RefundInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 订单编号 外联订单
	 */
	private Long orderSn;
	/**
	 * 退单申请id 
	 */
	private Long returnId;
	/**
	 *  启用状态
	 */
	private Integer status;

}
