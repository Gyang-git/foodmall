package com.atghy.foodmall.member.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集供应商信息 外联供应商原料信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-19 10:32:49
 */
@Data
@TableName("ufs_agent")
public class AgentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 供应商
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
	@TableField(exist = false)
	private Integer gender;
	/**
	 * 最近更新时间
	 */
	private Date updateTime;
	/**
	 * 代理级别 1->初级供应；2->中级供应；3->高级供应；4->特级供应
	 */
	private Integer agentLevel;
	/**
	 * 供应执照图片地址
	 */
	private String agentImgUrl;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
