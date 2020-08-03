package com.atghy.foodmall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集订单信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-02 16:29:45
 */
@Data
@TableName("ofs_order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 订单编号
	 */
	private Long orderSn;
	/**
	 * 派送编号 外联外卖派送信息
	 */
	private Long takeoutId;
	/**
	 * 健康监控id
	 */
	private Long healthId;
	/**
	 * 订单状态 1->待付款；2->待派送；3->派送中；4->待反馈；5->异常
	 */
	private Integer orderStatus;
	/**
	 * 下单顾客id 外联顾客
	 */
	private Long customerId;
	/**
	 * 接收人姓名
	 */
	private String takeName;
	/**
	 * 接收人电话
	 */
	private String takePhone;
	/**
	 * 订单总额
	 */
	private BigDecimal totalAmout;
	/**
	 * 实付金额
	 */
	private BigDecimal payAmout;
	/**
	 * 优惠金额
	 */
	private BigDecimal discountAmout;
	/**
	 * 外卖费用
	 */
	private BigDecimal freightAmout;
	/**
	 * 支付方式 1->支付宝；2->微信；3->校园一卡通；4->送达付款
	 */
	private Integer payWay;
	/**
	 * 地址一级分类
	 */
	private Integer addressFirst;
	/**
	 * 地址二级分类
	 */
	private Integer addressSecond;
	/**
	 * 地址三级分类
	 */
	private Integer addressThird;
	/**
	 * 成交获得积分
	 */
	private BigDecimal score;
	/**
	 * 启用状态
	 */
	private Integer status;
	/**
	 * 备注
	 */
	private String note;

}
