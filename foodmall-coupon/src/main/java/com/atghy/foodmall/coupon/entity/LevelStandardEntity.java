package com.atghy.foodmall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集用户优惠标准
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-30 12:30:16
 */
@Data
@TableName("cfs_level_standard")
public class LevelStandardEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 等级
	 */
	private String level;
	/**
	 * 优惠折扣
	 */
	private String discount;
	/**
	 * 基本分-最低达标分
	 */
	private String score;
	/**
	 *  启用状态
	 */
	@TableLogic
	private Integer status;

}
