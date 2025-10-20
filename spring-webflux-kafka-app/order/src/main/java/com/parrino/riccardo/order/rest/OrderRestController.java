package com.parrino.riccardo.order.rest;

import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.parrino.riccardo.models.InventoryRequest;

import reactor.core.publisher.Mono;

@RestController
public class OrderRestController {
    
    @GetMapping("/order/api/place/{productId}/{quantity}")
    public Mono<ResponseEntity<String>> placeOrder(@PathVariable Long productId, @PathVariable Integer quantity) {
        String collaborationId = UUID.randomUUID().toString();

        InventoryRequest request = InventoryRequest.builder()
            .orderId(1l)
            .collaborationId(collaborationId)
            .productId(productId)
            .quantity(quantity)
        .build();

        return null;
    }
    

}
