package com.hkdemircan.rabbitmq.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Component
@Configuration
public class RabbitMqConfig {
    //config
    @Value("${spring.rabbitmq.host}")
    private String brokerHost;
    @Value("${spring.rabbitmq.username}")
    private String brokerUsername;
    @Value("${spring.rabbitmq.password}")
    private  String brokerPassword;


    //user register
    @Value("${app.message.queue.user.register.exchange}")
    private String userRegisterExchange;
    @Value("${app.message.queue.user.register.event.name}")
    private String userRegisterEventQueue;
    @Value("${app.message.queue.user.register.event.routing.key}")
    private String userRegisterEventRoutingKey;

    //user-login
    @Value("${app.message.queue.user.login.exchange}")
    private String userLoginExchange;
    @Value("${app.message.queue.user.login.event.name}")
    private String userLoginEventQueue;


}
