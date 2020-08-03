package com.atghy.foodmall.order.interceptor;

import com.atghy.foodmall.common.constant.AuthServerConstant;
import com.atghy.foodmall.common.vo.CustomerResponseVo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-02
 * Description: 登录拦截器
 */
@Component
public class LoginUserInterceptor implements HandlerInterceptor {

    public static ThreadLocal<CustomerResponseVo> loginUser = new ThreadLocal<>();

    //方法执行之前执行该拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CustomerResponseVo attribute = (CustomerResponseVo) request.getSession().getAttribute(AuthServerConstant.LOGIN_USER);
        if (attribute != null){
            loginUser.set(attribute);
            //已经登录 放行
            return true;
        }else{
            //未登录 拦截请求 重定向到登录页面
            request.getSession().setAttribute("msg","请先完成登录操作");
            response.sendRedirect("http://auth.foodmall.com/login.html");
            return false;
        }
    }
}
