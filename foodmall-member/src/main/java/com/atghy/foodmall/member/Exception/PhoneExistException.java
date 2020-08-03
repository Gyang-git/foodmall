package com.atghy.foodmall.member.Exception;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-30
 * Description:
 */
public class PhoneExistException extends RuntimeException{
    public PhoneExistException(){
        super("该手机号已被注册");
    }
}
