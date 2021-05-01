package com.hkdemircan.rabbitmq.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Component
@Configuration
public class RabbitMqConfig {
    //user register
    @Value("${app.message.queue.user.register.exchange}")
    private String userRegisterExchange;

    @Value("${app.message.queue.user.register.event.name}")
    private String userRegisterEventQueue;

    @Value("${app.message.queue.user.register.event.routing.key}")
    private String userRegisterEventRoutingKey;
}
