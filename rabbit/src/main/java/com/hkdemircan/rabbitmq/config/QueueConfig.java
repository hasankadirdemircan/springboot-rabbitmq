package com.hkdemircan.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class QueueConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final RabbitMqConfig rabbitMqConfig;
    public QueueConfig(RabbitMqConfig rabbitMqConfig) {
        this.rabbitMqConfig = rabbitMqConfig;
    }

    @Bean
    public Queue queue(){
        return new Queue(rabbitMqConfig.getUserRegisterEventQueue());
    }

    @Bean
    public DirectExchange directExchange(){
       return new DirectExchange(rabbitMqConfig.getUserRegisterExchange());
    }

    @Bean
    public Binding binding(final Queue queue, final DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(rabbitMqConfig.getUserRegisterEventRoutingKey());
    }

}
