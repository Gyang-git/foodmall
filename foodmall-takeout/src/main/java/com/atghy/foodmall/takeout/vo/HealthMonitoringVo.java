package com.atghy.foodmall.takeout.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-17
 * Description:
 */
@Data
public class HealthMonitoringVo {
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
    private Integer status;
}
