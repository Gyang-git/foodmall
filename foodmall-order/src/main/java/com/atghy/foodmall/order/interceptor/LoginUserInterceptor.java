package com.atghy.foodmall.order.interceptor;

import com.atghy.foodmall.common.constant.AuthServerConstant;
import com.atghy.foodmall.common.vo.CustomerResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-02
 * Description: 登录拦截器
 */
@Slf4j
@Component
public class LoginUserInterceptor implements HandlerInterceptor {

    public static ThreadLocal<CustomerResponseVo> loginUser=new InheritableThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String uri  = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean statusMatch = antPathMatcher.match("/order/order/status/**", uri);//根据订单号查询订单
        boolean payedMatch = antPathMatcher.match("/payed/notify", uri);//支付宝异步回调
        boolean queryPayMatch = antPathMatcher.match("/queryPayStatus", uri); //支付宝查询支付状态接口
        boolean backMatch = antPathMatcher.match("/**/back/**",uri);//匹配所有后台接口
        if(statusMatch || payedMatch||queryPayMatch || backMatch){
            return true;
        }

        CustomerResponseVo attribute = (CustomerResponseVo)request.getSession().getAttribute(AuthServerConstant.LOGIN_USER);
        if(attribute!=null){
            loginUser.set(attribute);
            return true;
        }else {
            request.getSession().setAttribute("msg","请先登录！");
            response.sendRedirect("http://auth.foodmall.com/login.html");
            //没登陆 去登录
            return false;
        }
    }
}
