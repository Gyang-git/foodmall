package com.atghy.foodmall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 记录向供应商采购的信息表
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-07-25 10:38:18
 */
@Data
@TableName("wfs_ware_in_info")
public class WareInInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 原料id
	 */
	@TableId
	private Long rawId;
	/**
	 * 采购数量
	 */
	private Long inQuantity;
	/**
	 * 入库时间
	 */
	private Date time;
	/**
	 * 原料名称 外联供应商供应原料
	 */
	private String rawName;
	/**
	 * 防疫标准 1->一级防疫；2->二级防疫；3->三级防疫；4->红色防疫
	 */
	private Integer quarantineStandard;
	/**
	 * 1->生鲜肉；2->冷冻肉；3->密封物；4->野味；5->配料 
	 */
	private Integer sort;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
