package com.atghy.foodmall.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-29
 * Description:
 */
@Controller
public class LoginController {

    @GetMapping("/reg.html")
    public String regPage(){
        //用户尚未登录 返回登录
        return "reg";
    }

    @GetMapping("/login.html")
    public String loginPage(){
        System.out.println("login--");
        //用户尚未登录 返回登录
        return "login";
    }
}
