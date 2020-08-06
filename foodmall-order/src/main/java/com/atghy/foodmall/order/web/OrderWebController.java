package com.atghy.foodmall.order.web;

import com.atghy.foodmall.common.exception.NoStockException;
import com.atghy.foodmall.order.service.OrderService;
import com.atghy.foodmall.order.vo.OrderConfirmVo;
import com.atghy.foodmall.order.vo.OrderSubmitVo;
import com.atghy.foodmall.order.vo.SubmitOrderResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-03
 * Description:
 */
@Controller
public class OrderWebController {

    @Autowired
    OrderService orderService;

    //去结算
    @GetMapping("/toTrade")
    public String toTrace(Model model) throws ExecutionException, InterruptedException {
        OrderConfirmVo oderConfirmVo = orderService.confirmOrder();
        model.addAttribute("data",oderConfirmVo);
        return "confirm";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo vo, Model model, RedirectAttributes redirectAttributes){
        try {
            SubmitOrderResponseVo responseVo = orderService.submitOrder(vo);
            if (responseVo.getCode() == 0){
                //下单成功 前往支付页面
                model.addAttribute("submitOrderResp",responseVo);
                return "pay";
            }else{
                String msg = "下单失败--";
                switch (responseVo.getCode()){
                    case 1:
                        msg += "订单信息过期 请刷新再次提交";
                        break;
                    case 2:
                        msg += "订单餐品价格发生变化 请再次确认后重新提交";
                        break;
                    case 3:
                        msg += "库存锁定失败 餐品库存不足";
                        break;
                }
                redirectAttributes.addFlashAttribute("msg",msg);
                //下单失败 返回confirm重新提交
                return "redirect:http://order.foodmall.com/toTrade";
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NoStockException){
                String message=((NoStockException)e).getMessage();
                redirectAttributes.addFlashAttribute("msg",message);
            }
            return "redirect:http://order.foodmall.com/toTrace";
        }
    }
}
