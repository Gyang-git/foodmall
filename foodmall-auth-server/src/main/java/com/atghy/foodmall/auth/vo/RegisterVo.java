package com.atghy.foodmall.auth.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-30
 * Description:
 */
@Data
public class RegisterVo {
    @NotEmpty(message = "用户名必须填写")
    private String userName;

    @NotEmpty(message = "登录密码必须填写")
    @Length(min = 8,max = 16,message = "请按规定长度设置密码")
    private String password;

    @NotEmpty(message = "联系电话必须填写")
    @Pattern(regexp = "^[1][0-9]{10}",message = "手机号格式错误")
    private String phone;

    @NotEmpty(message = "验证码必须填写")
    private String code;
}
