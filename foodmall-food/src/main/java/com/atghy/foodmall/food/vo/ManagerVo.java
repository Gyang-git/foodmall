package com.atghy.foodmall.food.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-24
 * Description:
 */
@Data
public class ManagerVo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 饭店id
     */
    private Long restaurantId;
    /**
     * 昵称
     */
    private String name;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 企业营业执照图片
     */
    private String busineseImgUrl;
    /**
     * 卫生检测执照图片
     */
    private String sanitationImgUrl;
    /**
     * 最近更新时间
     */
    private Date updateTime;
    /**
     * 启用状态
     */
    private Integer status;
}
