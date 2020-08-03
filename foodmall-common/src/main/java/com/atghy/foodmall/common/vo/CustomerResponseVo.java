package com.atghy.foodmall.common.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-30
 * Description:
 */
@ToString
@Data
public class CustomerResponseVo implements Serializable {
    /**
     * 顾客编号
     */
    private Long uuid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String nickname;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别 1->男；2->女
     */
    private Integer gender;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 最近更新时间
     */
    private Date updateTime;
    /**
     * 用户来源
     */
    private Integer sourceType;
    /**
     * 用户当前积分
     */
    private Integer score;

    private String socialUid;
    private String accessToken;
    private Long expiresIn;
}
