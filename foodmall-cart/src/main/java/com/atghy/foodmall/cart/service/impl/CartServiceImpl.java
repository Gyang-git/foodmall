package com.atghy.foodmall.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atghy.foodmall.cart.feign.FoodFeignService;
import com.atghy.foodmall.cart.interpector.CartInterpector;
import com.atghy.foodmall.cart.service.CartService;
import com.atghy.foodmall.cart.vo.Cart;
import com.atghy.foodmall.cart.vo.CartItem;
import com.atghy.foodmall.cart.vo.SkuInfoVo;
import com.atghy.foodmall.cart.vo.UserInfoVo;
import com.atghy.foodmall.common.utils.R;
import com.mysql.cj.xdevapi.Type;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-31
 * Description:
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    FoodFeignService foodFeignService;

    private final String CART_PREFIX = "foodmall:cart";

    @Override
    public Cart getCart() {
        Cart cart = new Cart();
        //每个浏览器都有单一一个线程 整个线程的cookie共享
        UserInfoVo userInfoVo = CartInterpector.threadLocal.get();
        if (userInfoVo.getUuid() != null){
            //已经登录
            String cartKet = CART_PREFIX + userInfoVo.getUuid();
            //临时购物车的数据未合并
            //获取临时购物车的数据
            String tempCartKey = CART_PREFIX + userInfoVo.getUserKey();
            List<CartItem> tempCartItems = getCartItems(tempCartKey);
            if (tempCartItems != null){
                //临时购物车有数据 进行合并操作
                for (CartItem item : tempCartItems) {
                    if (item.getSingleId() != null){
                        addToCart(item.getSingleId(),item.getCount(),"single");
                    }else{
                        addToCart(item.getSetmealId(),item.getCount(),"setmeal");
                    }
                }
                clearCart(tempCartKey);
            }
            //获取登录后的购物车的数据 包括合并的临时数据以及原先的数据
            List<CartItem> cartItems = getCartItems(cartKet);
            cart.setItems(cartItems);
        }else {
            //没登录
            String cartKey = CART_PREFIX + userInfoVo.getUserKey();
            //获取临时购物车的所有购物项
            List<CartItem> cartItems = getCartItems(cartKey);
            cart.setItems(cartItems);
        }
        return cart;
    }

    //清除临时购物车
    private void clearCart(String tempCartKey) {
        redisTemplate.delete(tempCartKey);
    }

    //TODO 实现异步操作
    @Override
    public CartItem addToCart(Long skuId, Integer count,String name) {
        BoundHashOperations<String,Object,Object> cartOps = getCartOps();
        String res = (String) cartOps.get(skuId.toString());
        if (StringUtils.isEmpty(res)){
            //购物车无此商品
            //新增商品到购物车
            CartItem cartItem = new CartItem();
            //异步 远程查询当前要添加的商品信息
            R r = foodFeignService.getSingleByName(name);
            if (r.getCode() == 0){
                SkuInfoVo skuInfoVo = r.getData("single", new TypeReference<SkuInfoVo>() {
                });
                cartItem.setCheck(true);
                cartItem.setCount(count);
                cartItem.setSingleId(skuId);
                cartItem.setImgUrl(skuInfoVo.getImgUrl());
                cartItem.setTitle(skuInfoVo.getTitle());
                cartItem.setPrice(skuInfoVo.getPrice());
            }else{
                R setmealInfo = foodFeignService.setmealInfo(skuId);
                SkuInfoVo setmeal = setmealInfo.getData("setmeal", new TypeReference<SkuInfoVo>() {
                });
                cartItem.setCheck(true);
                cartItem.setCount(count);
                cartItem.setSetmealId(skuId);
                cartItem.setImgUrl(setmeal.getImgUrl());
                cartItem.setTitle(setmeal.getTitle());
                cartItem.setPrice(setmeal.getPrice());
            }
            //TODO 远程查询并封装特性及套餐组合信息
            //转为JSON
            String s = JSON.toJSONString(cartItem);
            cartOps.put(skuId.toString(),s);
            return cartItem;
        }else{
            //购物车已经有该餐品 合并餐品
            CartItem cartItem = JSON.parseObject(res, CartItem.class);
            cartItem.setCount(cartItem.getCount() + count);
            cartOps.put(skuId.toString(),JSON.toJSONString(cartItem));
            return cartItem;
        }
    }

    @Override
    public CartItem getCartItem(Long skuId) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        String res = (String) cartOps.get(skuId.toString());
        CartItem cartItem = JSON.parseObject(res, CartItem.class);
        return cartItem;
    }

    @Override
    public List<CartItem> getUserCartItems() {
        UserInfoVo userInfoVo = CartInterpector.threadLocal.get();
        if (userInfoVo.getUuid() == null){
            //没登录
            return null;
        }else{
            String cartKey = CART_PREFIX + userInfoVo.getUuid();
            List<CartItem> cartItems = getCartItems(cartKey);
            //获取被选中的购物项
            List<CartItem> collect = cartItems.stream().filter(item -> item.getCheck()).map(item -> {
                if (item.getSingleId() != null) {
                    R r = foodFeignService.singleInfo(item.getSingleId());
                    SkuInfoVo single = r.getData("single", new TypeReference<SkuInfoVo>() {
                    });
                    //获得该sku的最新价格
                    BigDecimal price = single.getPrice();
                    item.setPrice(price);
                }else {
                    R r = foodFeignService.setmealInfo(item.getSetmealId());
                    SkuInfoVo setmeal = r.getData("setmeal", new TypeReference<SkuInfoVo>() {
                    });
                    BigDecimal price = setmeal.getPrice();
                    item.setPrice(price);
                }
                //收集并返回所有的购物项
                return item;
            }).collect(Collectors.toList());
            return collect;
        }
    }

    /**
     * 获取要操作的购物车
     * @return
     */
    private BoundHashOperations<String, Object, Object> getCartOps() {
        UserInfoVo userInfoVo = CartInterpector.threadLocal.get();
        String cartKey = "";
        if (userInfoVo.getUuid() != null){
            //已登录
            cartKey = CART_PREFIX + userInfoVo.getUuid();
        }else{
            //没登录 临时购物车
            cartKey = CART_PREFIX + userInfoVo.getUserKey();
        }
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(cartKey);
        return operations;
    }

    /**
     * 加载购物车项
     * @param
     * @return
     */
    private List<CartItem> getCartItems(String cartKey) {
        BoundHashOperations<String, Object, Object> hashOps = redisTemplate.boundHashOps(cartKey);
        List<Object> values = hashOps.values();
        if (values != null&&values.size()>0){
            List<CartItem> collect = values.stream().map(obj -> {
                String str = (String) obj;
                CartItem cartItem = JSON.parseObject(str, CartItem.class);
                return cartItem;
            }).collect(Collectors.toList());
            return collect;
        }
        return null;
    }
}
