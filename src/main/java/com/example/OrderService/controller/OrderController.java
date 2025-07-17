package com.example.OrderService.controller;

import com.example.OrderService.dto.Order;
import com.example.OrderService.dto.StatusMessageResponse;
import com.example.OrderService.dto.Product;
import com.example.OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<StatusMessageResponse> makeOrder(@RequestBody Order request) {
        return orderService.makeOrder(request);
    }

    @GetMapping("/quantity/{productId}")
    public int getQuantity(@PathVariable Long productId) {
        return orderService.getProductQuantity(productId);
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        return orderService.getProduct(productId);
    }
}
