package com.hkdemircan.rabbitmq.controller;

import com.hkdemircan.rabbitmq.model.User;
import com.hkdemircan.rabbitmq.producer.UserLoginProducer;
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

    @Autowired
    private UserLoginProducer userLoginProducer;

    @PostMapping("/register")
    public User sendToRegisterQueue(@RequestBody User user){
        System.out.println("sendtoQueue received register method : " + user.toString());
        userRegisterProducer.sendToRegisterQueue(user);
        return user;
    }

    @PostMapping("/login")
    public User sendToLoginQueue(@RequestBody User user){
        System.out.println("sendtoQueue received login method : " + user.toString());
        userLoginProducer.userLoginProducer(user);
        return user;
    }
}
