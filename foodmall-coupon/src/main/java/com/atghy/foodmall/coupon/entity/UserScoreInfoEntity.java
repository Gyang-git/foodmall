package com.atghy.foodmall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 收集顾客积分情况
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-30 12:30:16
 */
@Data
@TableName("cfs_user_score_info")
public class UserScoreInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 用户编号
	 */
	private Long uuid;
	/**
	 * 当前积分
	 */
	private String score;
	/**
	 * 用户等级
	 */
	private String level;
	/**
	 * 可享折扣
	 */
	private BigDecimal discount;
	/**
	 * 使用状态
	 */
	@TableLogic
	private Integer status;

}
