package com.atghy.foodmall.seckill.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-19
 * Description:
 */
@Data
public class SeckillSessionWithFoods {
    private Long id;

    private String name;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Date createTime;

    private List<SeckillFoodVo> relationFoods;
}
