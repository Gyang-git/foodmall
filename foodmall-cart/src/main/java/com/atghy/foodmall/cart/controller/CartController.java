package com.atghy.foodmall.cart.controller;

import com.atghy.foodmall.cart.service.CartService;
import com.atghy.foodmall.cart.vo.Cart;
import com.atghy.foodmall.cart.vo.CartItem;
import com.atghy.foodmall.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-31
 * Description:
 */
@Controller
public class CartController {
    @Autowired
    CartService cartService;

    /**
     * 更改数量
     * @param foodId
     * @param num
     * @return
     */
    @GetMapping("countItem")
    public String countItem(@RequestParam("foodId") Long foodId,
                            @RequestParam("num") Integer num){
        cartService.changeItemCount(foodId,num);
        return "redirect:http://cart.foodmall.com/cart.html";
    }

    /**
     * 删除单项
     * @param foodId
     * @return
     */
    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam("foodId") Long foodId){
        cartService.deleteItem(foodId);
        return "redirect:http://cart.foodmall.com/cart.html";
    }

    /**
     * 购物车选中/取消
     * @param foodId
     * @param check
     * @return
     */
    @GetMapping("/checkItem")
    public String checkItem(@RequestParam("foodId") Long foodId,@RequestParam("check") Integer check){
        cartService.checkItem(foodId,check);
        //重定向返回
        return "redirect:http://cart.foodmall.com/cart.html";
    }

    @GetMapping("/cart.html")
    public String cartListPage(Model model){
        Cart cart = cartService.getCart();
        model.addAttribute("cart",cart);
        return "cartList";
    }

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("skuId") Long skuId,
                            @RequestParam("num") Integer num,
                            @RequestParam("name")String name,
                            RedirectAttributes redirectAttributes){
        cartService.addToCart(skuId,num,name);
        redirectAttributes.addAttribute("skuId",skuId);
        //重定向 防重刷
        return "redirect:http://cart.foodmall.com/addToCartSuccess.html";
    }

    //跳转到成功页面
    @GetMapping("/addToCartSuccess.html")
    public String addToCartSuccess(@RequestParam("skuId") Long skuId,Model model){
        //再次查询购物车数据
        CartItem cartItem = cartService.getCartItem(skuId);
        model.addAttribute("item",cartItem);
        return "success";
    }

    /**
     * 获取当前用户购物车check=true为的购物项
     * @return
     */
    @GetMapping("/currentUserCartItems")
    @ResponseBody
    public List<CartItem> getCurrentUserCartItems(){
        List<CartItem> userCartItems = cartService.getUserCartItems();
        return userCartItems;
    }
}
