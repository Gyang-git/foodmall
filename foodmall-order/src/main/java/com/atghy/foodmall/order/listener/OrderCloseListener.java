package com.atghy.foodmall.order.listener;

import com.atghy.foodmall.order.entity.OrderEntity;
import com.atghy.foodmall.order.service.OrderService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-09
 * Description:
 */
@RabbitListener(queues = "order.release.order.queue")
@Component
public class OrderCloseListener {
    @Autowired
    OrderService orderService;

    @RabbitHandler
    public void listener(OrderEntity entity, Channel channel, Message message) throws IOException {
        System.out.println("发现订单过期 准备关闭订单" + entity);
        try {
            orderService.closeOrder(entity);
            //TODO 支付宝确认支付状态 收单
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }
}
