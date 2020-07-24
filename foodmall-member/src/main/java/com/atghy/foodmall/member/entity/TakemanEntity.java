package com.atghy.foodmall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集外卖员个人基础信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-19 10:32:49
 */
@Data
@TableName("ufs_takeman")
public class TakemanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 昵称
	 */
	private String name;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 最近派单时间
	 */
	private Date updateTime;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
