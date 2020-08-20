package com.atghy.foodmall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集厨师个人基础信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-16 14:39:12
 */
@Data
@TableName("ufs_worker")
public class WorkerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;

	private Long workerId;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 性别 1->男；2->女
	 */
	private Integer gender;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 
	 */
	private String workType;
	/**
	 * 实时体温
	 */
	private BigDecimal tem;
	/**
	 * 健康状况
	 */
	private String health;
	/**
	 * 生成时间
	 */
	private Date saveTime;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
