package com.atghy.foodmall.cart.service;

import com.atghy.foodmall.cart.vo.Cart;
import com.atghy.foodmall.cart.vo.CartItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-31
 * Description:
 */
public interface CartService {
    Cart getCart();

    CartItem addToCart(Long skuId, Integer num,String name);

    CartItem getCartItem(Long skuId);

    List<CartItem> getUserCartItems();

}
