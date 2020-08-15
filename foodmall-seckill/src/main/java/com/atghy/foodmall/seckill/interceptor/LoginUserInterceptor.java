package com.atghy.foodmall.seckill.interceptor;

import com.atghy.foodmall.common.constant.AuthServerConstant;
import com.atghy.foodmall.common.vo.CustomerResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-02
 * Description: 登录拦截器
 */
@Slf4j
@Component
public class LoginUserInterceptor implements HandlerInterceptor {

    public static ThreadLocal<CustomerResponseVo> loginUser = new InheritableThreadLocal<>();

    /**
     * 拦截器 在方法执行之前进行拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
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
