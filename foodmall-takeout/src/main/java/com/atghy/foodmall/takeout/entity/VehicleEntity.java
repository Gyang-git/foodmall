package com.atghy.foodmall.takeout.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集外卖员所有车辆
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@Data
@TableName("tfs_vehicle")
public class VehicleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 车辆名称
	 */
	private String vehicleName;
	/**
	 * 车辆类别
	 */
	private String sort;
	/**
	 * 车辆价值
	 */
	private Long value;
	/**
	 * 购入时间
	 */
	private Date buyTime;
	/**
	 * 最近维修时间
	 */
	private Date updateTime;
	/**
	 * 最近检修时间
	 */
	private Date overhaulTime;
	/**
	 * 当前使用状态
	 */
	private Integer useStatus;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
