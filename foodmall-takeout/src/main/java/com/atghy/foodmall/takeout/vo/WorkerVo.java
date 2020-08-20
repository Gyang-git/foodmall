package com.atghy.foodmall.takeout.vo;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class WorkerVo {
    /**
     * 主键
     */
    private Long id;

    private Long workerId;
    /**
     * 用户名
     */
    private String name;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 性别 1->男；2->女
     */
    private Integer gender;
    /**
     * 生日
     */
    private Date birthday;
    /**
     *
     */
    private String workType;
    /**
     * 实时体温
     */
    private BigDecimal tem;
    /**
     * 健康状况
     */
    private String health;
    /**
     * 生成时间
     */
    private Date saveTime;
    /**
     * 启用状态
     */
    private Integer status;
}
