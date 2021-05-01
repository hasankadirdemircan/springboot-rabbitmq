package com.hkdemircan.rabbitmq.service;

import com.google.gson.Gson;
import com.hkdemircan.rabbitmq.config.RabbitMqConfig;
import com.hkdemircan.rabbitmq.model.User;
import com.hkdemircan.rabbitmq.publisher.GenericPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService {
    @Autowired
    private RabbitMqConfig rabbitMqConfig;

    @Autowired
    private GenericPublisher genericPublisher;

    @Autowired
    private Gson gson;
    private String userLoginExchange;
    private String userRegisterExchange;
    private String userRegisterEventRoutingKey;


    @PostConstruct
    public void init() {
        userLoginExchange = rabbitMqConfig.getUserLoginExchange();
        userRegisterExchange = rabbitMqConfig.getUserRegisterExchange();
        userRegisterEventRoutingKey = rabbitMqConfig.getUserRegisterEventRoutingKey();
    }

    public void userRegister(User user){
        try{
            genericPublisher.publish(userRegisterExchange, userRegisterEventRoutingKey, gson.toJson(user));
            System.out.println("User login event sent to queue -> " + user.toString());
        }catch (Exception e) {
            System.out.println(e);
        }

    }
    public void userLogin(User user){
        try{
            genericPublisher.publish(userLoginExchange, "", gson.toJson(user));
            System.out.println("User login event sent to queue -> " + user.toString());
        }catch (Exception e) {
            System.out.println(e);
        }

    }

}
