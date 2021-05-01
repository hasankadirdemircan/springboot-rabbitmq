package com.hkdemircan.rabbitmq.consumer;

import com.hkdemircan.rabbitmq.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterConsumer {

    @RabbitListener(queues = {"${app.message.queue.user.register.event.name}"}
    )
    public void handleNMessage(User user){
        System.out.println("User Register event received -> " + user.toString());
    }
}
