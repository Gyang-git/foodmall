package com.atghy.foodmall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集订单退货申请
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-02 16:29:45
 */
@Data
@TableName("ofs_order_return_apply")
public class OrderReturnApplyEntity implements Serializable {
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
	 * 退单编号
	 */
	private Long returnId;
	/**
	 * 退单理由
	 */
	private String reason;
	/**
	 * 退单状态 1->批阅中；2->批准；3->强制驳回；
	 */
	private Integer returnStatus;
	/**
	 * 驳回理由
	 */
	private String refuseReason;
	/**
	 * 扣除积分
	 */
	private BigDecimal deduct;
	/**
	 * 启用状态
	 */
	private Integer status;

}
