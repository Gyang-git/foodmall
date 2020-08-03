package com.atghy.foodmall.cart.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-07-31
 * Description: 餐品项
 */
public class CartItem {

    private Long skuId;

    private Long singleId;

    private Long setmealId;

    private String type;

    private Boolean check = true;

    private String title;

    private String imgUrl;

    private Integer count;

    private BigDecimal price;

    private String categoryName;

    private BigDecimal totalPrice;

    public Long getSingleId() {
        return singleId;
    }

    public void setSingleId(Long singleId) {
        this.singleId = singleId;
    }

    public Long getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(Long setmealId) {
        this.setmealId = setmealId;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    //计算当前项总价
    public BigDecimal getTotalPrice() {
        return this.price.multiply(new BigDecimal("" + this.count));
    }

}
