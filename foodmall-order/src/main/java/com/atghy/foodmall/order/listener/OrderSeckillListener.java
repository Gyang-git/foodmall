package com.atghy.foodmall.order.listener;

import com.atghy.foodmall.common.to.mq.SeckillOrderTo;
import com.atghy.foodmall.order.service.OrderService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-15
 * Description:
 */
@Slf4j
@RabbitListener(queues = "order.seckill.order.queue")
@Component
public class OrderSeckillListener {
    @Autowired
    OrderService orderService;

    public void listener(SeckillOrderTo seckillOrderTo, Channel channel, Message message) throws IOException {
        try {
            log.info("准备创建秒杀单的详细信息--"+seckillOrderTo);
            //创建秒杀单的详细信息
            orderService.createSeckillOrder(seckillOrderTo);
            //手动收单
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }
}
