package com.atghy.foodmall.order.web;

import com.alipay.api.AlipayApiException;
import com.atghy.foodmall.order.config.AliPayTemplate;
import com.atghy.foodmall.order.service.OrderService;
import com.atghy.foodmall.order.vo.PayVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-12
 * Description:
 */
@Controller
public class PayWebController {
    @Autowired
    OrderService orderService;

    @Autowired
    AliPayTemplate aliPayTemplate;

    @ResponseBody
    @GetMapping(value = "/payOrder",produces = "text/html") //设置直接返回一个页面
    public String aliPayOrder(@RequestParam("orderSn") String orderSn) throws AlipayApiException {
        System.out.println("支付宝支付-->" + orderSn);
        PayVo payVo = orderService.getOrderPay(orderSn);
        //返回一个页面 将该页面直接让浏览器显示
        String pay = aliPayTemplate.pay(payVo);
        System.out.println("开始支付");
        return pay;
    }
}
