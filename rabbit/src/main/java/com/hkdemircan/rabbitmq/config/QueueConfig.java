package com.hkdemircan.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Queue userLoginWelcomeOfferQueue() {
        return new Queue(rabbitMqConfig.getUserLoginEventQueue());
    }

    @Bean
    public FanoutExchange userLoginWelcomeOfferExchange() {
        return new FanoutExchange(rabbitMqConfig.getUserLoginExchange());
    }

    @Bean
    public Binding bindingUserLoginWelcomeOfferExhange(
            Queue userLoginWelcomeOfferQueue,
            FanoutExchange userLoginWelcomeOfferExchange
    ) {
        return BindingBuilder.bind(userLoginWelcomeOfferQueue).to(userLoginWelcomeOfferExchange);
    }

}
