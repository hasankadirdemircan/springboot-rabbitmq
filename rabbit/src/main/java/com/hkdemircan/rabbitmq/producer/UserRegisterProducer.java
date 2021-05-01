package com.hkdemircan.rabbitmq.producer;

import com.hkdemircan.rabbitmq.config.RabbitMqConfig;
import com.hkdemircan.rabbitmq.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserRegisterProducer {

    @Autowired
    private RabbitMqConfig rabbitMqConfig;

    private String userRegisterExchange;
    private String userRegisterEventRoutingKey;

    @PostConstruct
    public void init() {
        userRegisterExchange = rabbitMqConfig.getUserRegisterExchange();
        userRegisterEventRoutingKey = rabbitMqConfig.getUserRegisterEventRoutingKey();
    }
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToQueue(User user){
        try{
            rabbitTemplate.convertAndSend(userRegisterExchange, userRegisterEventRoutingKey, user);
            System.out.println("User register event sent to queue -> " + user.toString());
        }catch (Exception e) {
            System.out.println(e);
        }

    }
}
