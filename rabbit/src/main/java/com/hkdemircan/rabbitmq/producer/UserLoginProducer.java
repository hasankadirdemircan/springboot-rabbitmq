package com.hkdemircan.rabbitmq.producer;

import com.hkdemircan.rabbitmq.config.RabbitMqConfig;
import com.hkdemircan.rabbitmq.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserLoginProducer {
    @Autowired
    private RabbitMqConfig rabbitMqConfig;
    private String userLoginExchange;

    @PostConstruct
    public void init() {
        userLoginExchange = rabbitMqConfig.getUserLoginExchange();
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void userLoginProducer(User user){
        try{
            rabbitTemplate.convertAndSend(userLoginExchange, "", user);
            System.out.println("User login event sent to queue -> " + user.toString());
        }catch (Exception e) {
            System.out.println(e);
        }

    }
}
