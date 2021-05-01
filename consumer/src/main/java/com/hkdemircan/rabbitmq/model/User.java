package com.hkdemircan.rabbitmq.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String phone;
}
