package com.parrino.riccardo.demo.rest;

import org.springframework.web.bind.annotation.RestController;

import com.parrino.riccardo.demo.model.Order;
import com.parrino.riccardo.demo.service.OrderService;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class OrderRestController {

    @Autowired
    private OrderService orderService;
    
    @GetMapping("/api/order/place")
    public Mono<Order> placeOrder(@RequestParam Long productId, @RequestParam Integer quantity) {
        return orderService.placeOrder(productId, quantity);
    }

}
