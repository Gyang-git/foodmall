package com.atghy.foodmall.takeout.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 记录本次交易反馈
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@Data
@TableName("tfs_evaluate_info")
public class EvaluateInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 派送信息单 外联外卖派送信息
	 */
	private Long outId;
	/**
	 * 订单编号
	 */
	private Long orderSn;
	/**
	 * 反馈菜品 外联菜品特性
	 */
	private String natureName;
	/**
	 * 反馈评分
	 */
	private String evaluateScore;
	/**
	 * 启用状态
	 */
	private Integer status;

}
