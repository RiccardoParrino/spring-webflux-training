package com.parrino.riccardo.demo.rest;

import org.springframework.web.bind.annotation.RestController;

import com.parrino.riccardo.demo.model.Order;
import com.parrino.riccardo.demo.service.OrderService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class OrderRestController {

    @Autowired
    private OrderService orderService;
    
    @GetMapping("/api/order/place/{productId}/{quantity}")
    public Mono<Order> placeOrder(@PathVariable Long productId, @PathVariable Integer quantity) {
        return orderService.placeOrder(productId, quantity);
    }

    @GetMapping("/api/order/findAll")
    public Flux<Order> findAll() {
        return orderService
            .findAll()
            .skip(3)
            .take(3);
    }

    @GetMapping("/api/order/findAll/product/{productId}")
    public Flux<Order> findAllOrderByProductId(@PathVariable Long productId) {
        return orderService
            .findAll()
            .filter(data -> data.getOrderId() == productId);
    }

    @GetMapping("/api/order/findAll/quantity/{quantity}")
    public Flux<Order> findAllOrderByMinimumQuantity(@PathVariable Integer quantity) {
        return orderService.findAll()
            .filter( data -> data.getQuantity() >= quantity );
    }
    
    @GetMapping("/api/order/remove/{orderId}")
    public Mono<ResponseEntity<String>> removeOrder(@PathVariable Long orderId) {
        return orderService
            .deleteOrder(orderId)
                .map(data -> ResponseEntity.ok("Order with order-id: " + orderId + " removed successfully"));
    }
    
}
