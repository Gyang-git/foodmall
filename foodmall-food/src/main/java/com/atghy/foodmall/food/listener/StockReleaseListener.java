package com.atghy.foodmall.food.listener;

import com.atghy.foodmall.common.to.mq.OrderTo;
import com.atghy.foodmall.common.to.mq.StockLockedTo;
import com.atghy.foodmall.food.service.FoodStockService;
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
 * Date: 2020-08-07
 * Description:
 * 消息队列：用于监听库存消息
 */
@RabbitListener(queues = "stock.release.stock.queue")
@Component
public class StockReleaseListener {
    @Autowired
    FoodStockService foodStockService;

    @RabbitListener
    public void handleStockLockedRelease(StockLockedTo to, Message message, Channel channel) throws IOException {
        System.out.println("收到解锁库存消息");
        //解锁库存
        try {
            foodStockService.unlockStock(to);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            //若消息遭到拒绝 则重新放回队列
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }

    @RabbitHandler
    public void handleOrderCloseReleas(Message message, Channel channel, OrderTo orderTo) throws IOException {
        System.out.println("接收到订单关闭消息 开始解锁库存");
        try {
            foodStockService.unlockStock(orderTo);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }
}
