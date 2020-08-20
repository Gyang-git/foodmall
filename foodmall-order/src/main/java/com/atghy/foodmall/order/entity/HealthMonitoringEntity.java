package com.atghy.foodmall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-02 16:29:45
 */
@Data
@TableName("ofs_health_monitoring")
public class HealthMonitoringEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 厨师id
	 */
	private Long chefId;
	/**
	 * 厨师体温
	 */
	private BigDecimal chefTem;
	/**
	 * 厨师健康情况 0->健康;1->感冒;2->咳嗽;3->发烧;
	 */
	private String chefHeal;
	/**
	 * 包装员id
	 */
	private Long packerId;
	/**
	 * 包装员体温
	 */
	private BigDecimal packerTem;
	/**
	 * 包装员健康情况
	 */
	private String packerHeal;
	/**
	 * 配送员id
	 */
	private Long takemanId;
	/**
	 * 配送员体温
	 */
	private BigDecimal takemanTem;
	/**
	 * 配送员健康情况
	 */
	private String takemanHeal;
	/**
	 * 上报时间
	 */
	private Date updatetime;
	/**
	 * 启用状态
	 */
	@TableLogic
	private Integer status;

}
