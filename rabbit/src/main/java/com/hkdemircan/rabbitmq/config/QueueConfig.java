package com.hkdemircan.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_JSON_ALT;

@Configuration
public class QueueConfig {


    private final RabbitMqConfig rabbitMqConfig;
    public QueueConfig(RabbitMqConfig rabbitMqConfig) {
        this.rabbitMqConfig = rabbitMqConfig;
    }

    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitMqConfig.getBrokerHost());
        connectionFactory.setUsername(rabbitMqConfig.getBrokerUsername());
        connectionFactory.setPassword(rabbitMqConfig.getBrokerPassword());
        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
        return connectionFactory;
    }

    @Bean
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(2);
        factory.setMaxConcurrentConsumers(4);
        return factory;
    }

    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageProperties messageProperties() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(CONTENT_TYPE_JSON_ALT);
        messageProperties.setCorrelationId("hkdemircan"); //TODO:NPE, spring boot bug, need to be dynamic in future
        return messageProperties;
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
