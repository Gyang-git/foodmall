package com.atghy.foodmall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 秒杀餐品通知订阅
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-14 20:32:22
 */
@Data
@TableName("cfs_seckill_food_notice")
public class SeckillFoodNoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * member_id
	 */
	private Long uuif;
	/**
	 * food_id
	 */
	private Long foodId;
	/**
	 * 活动场次id
	 */
	private Long sessionId;
	/**
	 * 订阅时间
	 */
	private Date subcribeTime;
	/**
	 * 发送时间
	 */
	private Date sendTime;
	/**
	 * 通知方式[0-短信，1-邮件]
	 */
	private Integer noticeType;
	/**
	 * 
	 */
	private Integer status;

}
