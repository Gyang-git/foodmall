package com.atghy.foodmall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集代理商供应原料信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-25 10:38:18
 */
@Data
@TableName("wfs_agent_raw_info")
public class AgentRawInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 供应商名称 外联供应商
	 */
	private String agentName;
	/**
	 * 原料名称
	 */
	private String rawName;
	/**
	 * 计量单位
	 */
	private String unitOfMeasurement;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
