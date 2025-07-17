package com.example.OrderService.service;

import com.example.OrderService.dto.Order;
import com.example.OrderService.dto.StatusMessageResponse;
import com.example.OrderService.dto.Product;
import com.example.OrderService.kafka.OrderProducerKafka;
import com.example.OrderService.proto.ProductRequest;
import com.example.OrderService.proto.ProductResponse;
import com.example.OrderService.proto.ProductServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductServiceGrpc.ProductServiceBlockingStub productServiceStub;
    private final OrderProducerKafka orderProducerKafka;

    public Product getProduct(long productId) {
        ProductRequest request = ProductRequest.newBuilder()
                .setProductId(productId)
                .build();

        ProductResponse response = productServiceStub.getProductInfo(request);

        return new Product(response.getId(),
                           response.getName(),
                           response.getQuantity(),
                           response.getPrice(),
                           response.getSale());
    }

    public int getProductQuantity(long productId) {
        ProductRequest request = ProductRequest.newBuilder()
                .setProductId(productId)
                .build();

        ProductResponse response = productServiceStub.getProductInfo(request);

        return response.getQuantity();
    }

    public ResponseEntity<StatusMessageResponse> makeOrder(Order request) {
        try {
            orderProducerKafka.sendOrder(request);
            return ResponseEntity.ok().body(new StatusMessageResponse(200, "Order successfully created"));
        } catch (Exception e) {
         return ResponseEntity.status(418).body(new StatusMessageResponse(418, "Some Exception"));
        }
    }
}
