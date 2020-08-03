package com.atghy.foodmall.order.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-02
 * Description:
 */
public class OrderConfirmVo {

    @Getter
    @Setter
    private Address address;

    @Getter
    @Setter
    private List<OrderItemVo> items;

    //库存状态
    @Getter
    @Setter
    private Map<Long,Boolean> stocks;

    @Getter
    @Setter
    //订单防重令牌 防重刷
    private String orderToken;

    //完成订单奖励积分数
    @Getter
    @Setter
    private Integer score;

    //商品总件数
    public Integer getCount(){
        Integer i=0;
        if(items!=null){
            for (OrderItemVo item : items) {
                i+=item.getCount();
            }
        }
        return i;
    }

    public BigDecimal getTotal() {
        BigDecimal sum = new BigDecimal("0");
        if(items!=null){
            for (OrderItemVo item : items) {
                BigDecimal multiply = item.getPrice().multiply(new BigDecimal(item.getCount().toString()));
                sum=sum.add(multiply);
            }
        }
        return sum;
    }

    //返回结算总价
    public BigDecimal getPayPrice() {
        return getTotal();
    }

    //地址
    @Data
    private class Address {
        /**
         * 地址一级分类
         */
        private Integer addressFirst;
        /**
         * 地址二级分类
         */
        private Integer addressSecond;
        /**
         * 地址三级分类
         */
        private Integer addressThird;
    }
}
