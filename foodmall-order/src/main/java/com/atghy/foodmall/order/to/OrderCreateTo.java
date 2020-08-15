package com.atghy.foodmall.order.to;

import com.atghy.foodmall.order.entity.OrderEntity;
import com.atghy.foodmall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-06
 * Description:
 */
@Data
public class OrderCreateTo {
    private OrderEntity order;

    //订单所包含的餐品
    private List<OrderItemEntity> items;

    //应付价格
    private BigDecimal payPrice;

    //派送费
    private BigDecimal fare;
}
