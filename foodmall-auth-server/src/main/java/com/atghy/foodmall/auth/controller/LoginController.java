package com.atghy.foodmall.auth.controller;

import com.alibaba.fastjson.TypeReference;
import com.atghy.foodmall.auth.feign.MemberFeignService;
import com.atghy.foodmall.auth.feign.ThirdPartyFeignService;
import com.atghy.foodmall.auth.vo.LoginVo;
import com.atghy.foodmall.auth.vo.RegisterVo;
import com.atghy.foodmall.common.constant.AuthServerConstant;
import com.atghy.foodmall.common.exception.BizCodeEnume;
import com.atghy.foodmall.common.utils.R;
import com.atghy.foodmall.common.vo.CustomerResponseVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-29
 * Description:
 */
@Controller
public class LoginController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    MemberFeignService memberFeignService;

    @Autowired
    ThirdPartyFeignService thirdPartyFeignService;

    @ResponseBody
    @GetMapping("/sms/sendcode")
    public R sendCode(@RequestParam("phone") String phone){
        System.out.println(phone);
        //1-接口防刷
        String redisCode = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone);
        if (!StringUtils.isEmpty(redisCode)){
            long l = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - 1 < 60000){
                //60秒内不能再次发送
                return R.error(BizCodeEnume.SMS_CODE_EXCEPTION.getCode(),BizCodeEnume.SMS_CODE_EXCEPTION.getMsg());
            }
        }
        String code = UUID.randomUUID().toString().substring(0, 5);
        String subString = code + "_" + System.currentTimeMillis();
        //2-验证码过期校验
        redisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone,subString,10, TimeUnit.MINUTES);
        thirdPartyFeignService.sendCode(phone,code);
        return R.ok();
    }

    @PostMapping("/regist")
    public String regist(@Valid RegisterVo vo, BindingResult result,
                         RedirectAttributes redirectAttributes,
                         HttpSession session){
        if (result.hasErrors()){
            //注册校验错误 转发重新注册
            //收集校验信息
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:http://auth.foodmall.com/reg.html";
        }
        String code = vo.getCode();
        String s = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
        if (!StringUtils.isEmpty(s)){
            if (code.equals(s.split("_")[0])){
                //验证码匹配 删除缓存
                redisTemplate.delete(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
                //验证码通过 调用远程保存数据11
                R r = memberFeignService.regist(vo);
                if (r.getCode() == 0){
                    //注册成功 返回登录
                    return "redirect:/login.html";
                }else{
                    //注册失败 收集错误信息
                    HashMap<String, String> errors = new HashMap<>();
                    redirectAttributes.addFlashAttribute("msg",new TypeReference<String>(){});
                    errors.put("msg",r.getData(new TypeReference<String>(){}));
                    return "redirect:http://auth.foodmall.com/reg.html";
                }
            }else{
                //验证码校验失败
                HashMap<String, String> errors = new HashMap<>();
                errors.put("code","验证码校验失败");
                redirectAttributes.addFlashAttribute("errors",errors);
                return "redirect:http://auth.foodmall.com/reg.html";
            }
        }else{
            HashMap<String, String> errors = new HashMap<>();
            errors.put("code","请填写验证码");
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:http://auth.foodmall.com/reg.html";
        }
    }

    @PostMapping("/login")
    public String login(LoginVo vo, RedirectAttributes redirectAttributes, HttpSession session){
        //远程登录
        R r = memberFeignService.login(vo);
        if (r.getCode() == 0){
            CustomerResponseVo loginUser = r.getData(new TypeReference<CustomerResponseVo>() {
            });
            session.setAttribute(AuthServerConstant.LOGIN_USER,loginUser);
            return "redirect:http://foodmall.com";
        }else{
            Map<String, String> errors = new HashMap<>();
            errors.put("msg",r.getData("msg",new TypeReference<String>(){}));
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:http://auth.foodmall.com/login.html";
        }
    }

    @GetMapping("/login.html")
    public String loginPage(HttpSession session){
        Object attribute = session.getAttribute(AuthServerConstant.LOGIN_USER);
        if(attribute==null){
            //没登陆
            return "login";
        }else {
            return "redirect:http://foodmall.com";
        }
    }
}
