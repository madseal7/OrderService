package com.example.OrderService.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Order {

    Long orderId;

    Product product;

    int quantity;

    Long userId;
}
