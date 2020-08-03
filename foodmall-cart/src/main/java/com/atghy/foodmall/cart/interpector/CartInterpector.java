package com.atghy.foodmall.cart.interpector;

import com.atghy.foodmall.cart.vo.UserInfoVo;
import com.atghy.foodmall.common.constant.AuthServerConstant;
import com.atghy.foodmall.common.constant.CartConstant;
import com.atghy.foodmall.common.vo.CustomerResponseVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.swing.StringUIClientPropertyKey;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-31
 * Description: 购物车拦截器 用于判断用户是否已经登录 并封装后传递给controller
 * 浏览器有一个cookie：user-key：区别用户身份 一个月后过期‘
 */
public class CartInterpector implements HandlerInterceptor {

    public static ThreadLocal<UserInfoVo> threadLocal = new InheritableThreadLocal<>();
    /**
     * 在业务执行之前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfoVo userInfoVo = new UserInfoVo();
        HttpSession session = request.getSession();
        CustomerResponseVo member = (CustomerResponseVo)session.getAttribute(AuthServerConstant.LOGIN_USER);
        if (member != null){
            //用户已经登录
            userInfoVo.setUuid(member.getUuid());
        }
        Cookie[] cookies = request.getCookies();
        //检查是否为临时用户 cookie
        if (cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (name.equals(CartConstant.TEMP_USER_COOKIE_NAME)){
                    userInfoVo.setUserKey(cookie.getValue());
                    userInfoVo.setTempUser(true);
                }
            }
        }
        //非临时用户 分配一个
        if (StringUtils.isEmpty(userInfoVo.getUserKey())){
            Long i =Long.valueOf(UUID.randomUUID().toString().replace("-", "").hashCode());
            Long uuid = i < 0 ? -i:i;
            userInfoVo.setUserKey(uuid.toString());
        }
        threadLocal.set(userInfoVo);
        return true;
    }

    /**
     * 业务执行之后
     * 生成浏览器保存
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserInfoVo userInfoVo = threadLocal.get();
        //没有临时用户 生成一个
        if (!userInfoVo.getTempUser()){
            //持续延长临时用户过期时间
            Cookie cookie = new Cookie(CartConstant.TEMP_USER_COOKIE_NAME, userInfoVo.getUserKey());
            cookie.setDomain("foodmall.com");
            cookie.setMaxAge(CartConstant.TEMP_USER_COOKIE_TIMEOUT);
            response.addCookie(cookie);
        }
    }
}
