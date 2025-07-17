package com.example.OrderService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    Long id;

    String name;

    int quantity;

    double price;

    boolean onSale;
}
