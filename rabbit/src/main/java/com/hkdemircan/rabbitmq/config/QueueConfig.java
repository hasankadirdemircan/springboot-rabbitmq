package com.hkdemircan.rabbitmq.config;

import org.springframework.amqp.core.*;
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

    //user-register
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

    //user-login
    @Bean
    public FanoutExchange userLoginExchange() {
        return new FanoutExchange(rabbitMqConfig.getUserLoginExchange());
    }

    @Bean
    public Queue userLoginQueue() {
        return new Queue(rabbitMqConfig.getUserLoginEventQueue());
    }

    @Bean
    public Binding bindUserLoginQueueToExchange(Queue userLoginQueue,
                                                FanoutExchange userLoginExchange) {
        return BindingBuilder.bind(userLoginQueue).to(userLoginExchange);
    }

}
