package com.atghy.foodmall.order.vo;

import com.atghy.foodmall.order.entity.OrderEntity;
import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-05
 * Description:
 */
@Data
public class SubmitOrderResponseVo {

    private OrderEntity order;

    /**
     * 订单提交返回状态码
     * 0--提交中
     */
    private Integer code;

    private String msg;
}
