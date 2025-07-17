package com.example.OrderService.dto;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String email;
    String password;
}
