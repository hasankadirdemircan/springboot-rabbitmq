package com.hkdemircan.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    private final RabbitMqConfig rabbitMqConfig;

    public QueueConfig(RabbitMqConfig rabbitMqConfig) {
        this.rabbitMqConfig = rabbitMqConfig;
    }

    //user-login
    @Bean
    public Queue userLoginQueue(){
        return new Queue(rabbitMqConfig.getUserLoginEventQueue());
    }

    @Bean
    public FanoutExchange userLoginExchange(){
        return new FanoutExchange(rabbitMqConfig.getUserLoginExchange());
    }

    @Bean
    public Binding bindingUserLogin(Queue userLoginQueue, FanoutExchange userLoginExchange){
        return BindingBuilder.bind(userLoginQueue).to(userLoginExchange);
    }
}

