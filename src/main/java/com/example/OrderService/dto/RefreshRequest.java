package com.example.OrderService.dto;

import lombok.Data;

@Data
public class RefreshRequest {
    String accessToken;
    String refreshToken;
}
