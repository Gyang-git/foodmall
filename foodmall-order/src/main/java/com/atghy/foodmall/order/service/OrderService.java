package com.atghy.foodmall.order.service;

import com.atghy.foodmall.common.to.mq.SeckillOrderTo;
import com.atghy.foodmall.order.to.PayAsyncVo;
import com.atghy.foodmall.order.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 收集订单信息
 *
 * @author GHYANG
 * @email 58132971@qq.com
 * @date 2020-08-02 16:29:45
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    OrderConfirmVo confirmOrder();

    SubmitOrderResponseVo submitOrder(OrderSubmitVo vo);

    void closeOrder(OrderEntity entity);

    FareInfoVo getOrderInfo(Long countTotal);

    PayVo getOrderPay(String orderSn);

    OrderEntity getOrderByOrderSn(String orderSn);

    PageUtils queryPageWithItem(Map<String, Object> params);

    /**
     * 支付宝支付成功异步回调 信息处理
     * @param vo
     * @return
     */
    String handPayResult(PayAsyncVo vo);

    /**
     * 创建秒杀单详细虚拟系
     * @param seckillOrderTo
     */
    void createSeckillOrder(SeckillOrderTo seckillOrderTo);
}

