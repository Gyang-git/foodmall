package com.atghy.foodmall.member.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-30
 * Description:
 */
@Data
public class UserScoreInfoVo {
    /**
     * 用户编号
     */
    private Long uuid;
    /**
     * 当前积分
     */
    private String score;
    /**
     * 用户等级
     */
    private String level;
    /**
     * 可享折扣
     */
    private Double discount;
    /**
     * 使用状态
     */
    private Integer status;
}
