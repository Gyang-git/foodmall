package com.atghy.foodmall.takeout.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 记录外卖可选地址一级分类
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@Data
@TableName("tfs_address_first")
public class AddressFirstEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 一级地址
	 */
	private String firstAddress;
	/**
	 * 启用状态
	 */
	private Integer status;

}
