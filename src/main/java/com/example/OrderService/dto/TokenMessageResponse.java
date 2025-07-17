package com.example.OrderService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenMessageResponse {
    int status;
    String accessToken;
    String refreshToken;
    String message;
}
