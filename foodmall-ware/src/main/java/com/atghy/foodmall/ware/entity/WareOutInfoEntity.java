package com.atghy.foodmall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收集仓库原料出货明细
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-25 10:38:18
 */
@Data
@TableName("wfs_ware_out_info")
public class WareOutInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 出货物品 外联库存信息表
	 */
	private String wareName;
	/**
	 * 出库数量
	 */
	private Long outQuantity;
	/**
	 * 出库申请人名称 外联餐饮负责人
	 */
	private String managerName;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
