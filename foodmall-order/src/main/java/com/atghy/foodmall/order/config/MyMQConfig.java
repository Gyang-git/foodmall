package com.atghy.foodmall.order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-09
 * Description:
 */
@Configuration
public class MyMQConfig {
    /**
     * 延时队列 死信队列
     * @return
     */
    @Bean
    public Queue orderDelayQueue(){
        Map<String, Object> arguments = new HashMap<>();
        //死信路由
        arguments.put("x-dead-letter-exchange","order-event-exchange");
        //死信路由键
        arguments.put("x-dead-letter-routing-key","order.release.order");
        //过期时间
        arguments.put("x-message-ttl",60000);
        Queue queue = new Queue("order.delay.queue", true, false, false, arguments);
        return queue;
    }

    @Bean
    public Queue orderReleaseOrderQueue(){
        Queue queue = new Queue("order.release.order.queue", true, false, false);
        return queue;
    }

    //交换机
    @Bean
    public Exchange orderEventExchange(){
        TopicExchange exchange = new TopicExchange("order-event-exchange", true, false);
        return exchange;
    }

    //订单的binding
    @Bean
    public Binding orderCreateOrderBinding(){
        Binding binding = new Binding("order.delay.queue", Binding.DestinationType.QUEUE, "order-event-exchange", "order.create.order", null);
        return binding;
    }

    /**
     * 订单释放和库存释放绑定
     * @return
     */
    @Bean
    public Binding orderReleaseBinding(){
        Binding binding = new Binding("stock.release.stock.queue", Binding.DestinationType.QUEUE, "order-event-exchange", "order.release.other.#", null);
        return binding;
    }

    @Bean
    public Queue orderSeckillOrderQueue(){
        Queue queue = new Queue("order.seckill.order.queue", true, false, false);
        return queue;
    }

    @Bean
    public Binding orderSeckillOrderBinding(){
        Binding binding = new Binding("order.seckill.order.queue", Binding.DestinationType.QUEUE, "order-event-exchang", "order.seckill.order", null);
        return binding;
    }
}
