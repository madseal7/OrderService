package com.example.OrderService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusMessageResponse {
    int status;
    String message;
}
