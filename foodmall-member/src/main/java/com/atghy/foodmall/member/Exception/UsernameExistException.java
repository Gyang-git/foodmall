package com.atghy.foodmall.member.Exception;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-30
 * Description:
 */
@Data
public class UsernameExistException extends RuntimeException{
    public UsernameExistException(){
        super("该用户名已被注册");
    }
}
