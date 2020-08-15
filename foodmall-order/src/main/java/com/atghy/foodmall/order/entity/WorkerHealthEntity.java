package com.atghy.foodmall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 记录员工每日健康状况
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-15 22:07:08
 */
@Data
@TableName("ofs_worker_health")
public class WorkerHealthEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 员工编号
	 */
	private Long workId;
	/**
	 * 员工种类
	 */
	private Integer workType;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 健康情况
	 */
	private Integer heal;
	/**
	 * 实时体温
	 */
	private BigDecimal tem;
	/**
	 * 保存日期
	 */
	private Date saveDate;
	/**
	 * 状态
	 */
	private Integer status;

}
