package com.atghy.foodmall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集库存量信息
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-25 10:38:18
 */
@Data
@TableName("wfs_ware_sku")
public class WareSkuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 原料供应商 外联供应商
	 */
	private String agentName;
	/**
	 * 原料名
	 */
	private String name;
	/**
	 * 库存量
	 */
	private Long quantity;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
