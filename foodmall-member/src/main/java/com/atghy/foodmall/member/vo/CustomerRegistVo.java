package com.atghy.foodmall.member.vo;

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
public class CustomerRegistVo {
    private String userName;

    private String password;

    private String phone;
}
