package com.atghy.foodmall.takeout.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 外卖可选地址三级分类
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@Data
@TableName("tfs_address_third")
public class AddressThirdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 一级分类id 外联外卖地址一级分类
	 */
	private Long firstId;
	/**
	 * 二级分类id 外联外卖地址二级分类
	 */
	private Long secondId;
	/**
	 * 三级地址
	 */
	private String thirdAddress;
	/**
	 * 启用状态
	 */
	private Integer status;

}
