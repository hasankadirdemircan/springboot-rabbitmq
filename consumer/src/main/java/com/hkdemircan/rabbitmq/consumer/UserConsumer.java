package com.hkdemircan.rabbitmq.consumer;

import com.hkdemircan.rabbitmq.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @RabbitListener(queues = {"${app.message.event.user-login}"})
    public void handleUserLogin(User user){
        System.out.println("User Login consumer ms event received -> " + user.toString());
    }

}
