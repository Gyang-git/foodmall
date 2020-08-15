package com.atghy.foodmall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集派送员个人基础信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-15 22:26:58
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
	 * 生成时间
	 */
	private Date saveTime;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
