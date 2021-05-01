package com.hkdemircan.rabbitmq.controller;

import com.hkdemircan.rabbitmq.model.User;
import com.hkdemircan.rabbitmq.producer.UserRegisterProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitSendQueueController {

    @Autowired
    private UserRegisterProducer userRegisterProducer;

    @PostMapping
    public User sendToQueue(@RequestBody User user){
        System.out.println("sendtoQueue received controller : " + user.toString());
        userRegisterProducer.sendToQueue(user);
        return user;
    }
}
