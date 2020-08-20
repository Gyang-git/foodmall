package com.atghy.foodmall.takeout.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 记录外卖派送信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-17 17:56:43
 */
@Data
@TableName("tfs_out_info")
public class OutInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 派送编号
	 */
	private String orderSn;

	private String takeSn;
	/**
	 * 开始派送时间
	 */
	private Date startTime;
	/**
	 * 送达派送时间
	 */
	private Date arriveTime;
	/**
	 * 所用车辆 外联外卖车辆
	 */
	private Long vehicleId;
	/**
	 * 外卖员名字
	 */
	private String takemanName;
	/**
	 * 启用状态
	 */
	private Integer status;

}
