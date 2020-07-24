package com.atghy.foodmall.food.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集单菜品制作所需原料
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-22 17:46:42
 */
@Data
@TableName("ffs_single_raw")
public class SingleRawEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 单菜品id
	 */
	private Long singleId;
	/**
	 * 原料名称 外联库存信息表
	 */
	private String rawName;
	/**
	 * 所需数量
	 */
	private Integer quantity;
	/**
	 * 库存状态
	 */
	private Integer stock;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 所属代理商
	 */
	private String agentName;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
