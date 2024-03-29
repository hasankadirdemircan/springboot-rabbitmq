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

    //user-login
    @Value("${app.message.internal.exchange.user-login}")
    private String userLoginExchange;
    @Value("${app.message.event.user-login}")
    private String userLoginEventQueue;

}
