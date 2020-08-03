package com.atghy.foodmall.cart.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-31
 * Description:
 */
@Data
public class Cart {
    //餐品单项
    List<CartItem> items;
    //总数量
    private Integer countNum;
    //餐品类型数量
    private Integer countType;
    //总价
    private BigDecimal totalAmount;
    //优惠价格
    private BigDecimal reduce = new BigDecimal("0.00");

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Integer getCountNum() {
        int count = 0;
        if(items != null && items.size() > 0){
            for (CartItem item : items) {
                count+=item.getCount();
            }
        }
        return count;
    }

    public Integer getCountType() {
        int count = 0;
        if (items != null && items.size()>0){
            for (CartItem item : items) {
                count += 1;
            }
        }
        return count;
    }


    public BigDecimal getTotalAmount() {
        BigDecimal amount = new BigDecimal("0");
        //计算购物项总价
        if (items != null && items.size() >0){
            for (CartItem item : items) {
                if (item.getCheck()){
                    BigDecimal totalPrice = item.getTotalPrice();
                    amount = amount.add(totalPrice);
                }
            }
        }
        //减去优惠总价
        BigDecimal subtract = amount.subtract(getReduce());
        return subtract;
    }


    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }
}
