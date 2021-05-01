package com.hkdemircan.rabbitmq.publisher;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class GenericPublisher {

    private RabbitTemplate rabbitTemplate;
    private MessageProperties messageProperties;

    public GenericPublisher(RabbitTemplate rabbitTemplate, MessageProperties messageProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageProperties = messageProperties;
    }

    public void publish(String exchange, String routingKey, String json) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, new Message(json.getBytes(), messageProperties));
        } catch (AmqpException e) {
            System.out.println(e);
        }
    }

}
