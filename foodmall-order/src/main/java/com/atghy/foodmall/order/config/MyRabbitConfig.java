package com.atghy.foodmall.order.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA
 * User: GHYANG
 * Date: 2020-08-09
 * Description:
 */
@Configuration
public class MyRabbitConfig {
    @Autowired
    CachingConnectionFactory cachingConnectionFactory;

    @Bean
    RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        //设置确认回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * 只要消息抵达服务器 ack就为true
             * @param correlationData 当前消息的唯一关联数据（消息唯一id）
             * @param b Rabbit是否成功收到消息
             * @param s 失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                /**
                 * 每一个发送的消息都存入数据库--mq_message表 需要定期将失败的消息再次发送
                 */
                System.out.println("confirm提交");
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 只要消息没有投递给指定队列 就触发这个失败回调
             * @param message   投递失败的消息详细信息
             * @param replyCode 回复状态码
             * @param replyText 回复的文本内容
             * @param exchange  当时这个消息发给哪个交换机
             * @param routingKey 当时这个消息用哪个路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                //报错了，修改数据库当前消息的状态 -》错误。
            }
        });
        return rabbitTemplate;
    }

    /**
     * 配置消息类型转换器
     * 使用json序列化 进行消息转换
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
