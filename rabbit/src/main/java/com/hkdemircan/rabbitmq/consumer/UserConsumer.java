package com.hkdemircan.rabbitmq.consumer;

import com.hkdemircan.rabbitmq.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @RabbitListener(queues = {"${app.message.queue.user.register.event.name}"})
    public void handleUserRegistration(User user){
        System.out.println("User Register event received -> " + user.toString());
    }

    @RabbitListener(queues = {"${app.message.queue.user.login.event.name}"})
    public void handleUserLogin(User user){
        System.out.println("User Login event received -> " + user.toString());
    }
}
