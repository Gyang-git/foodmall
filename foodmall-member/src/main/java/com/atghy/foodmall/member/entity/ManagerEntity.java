package com.atghy.foodmall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集餐饮负责人个人信息及饭店关联
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-19 10:32:49
 */
@Data
@TableName("ufs_manager")
public class ManagerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 饭店id
	 */
	private Long restaurantId;
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
	 * 企业营业执照图片
	 */
	private String busineseImgUrl;
	/**
	 * 卫生检测执照图片
	 */
	private String sanitationImgUrl;
	/**
	 * 最近更新时间
	 */
	private Date updateTime;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
