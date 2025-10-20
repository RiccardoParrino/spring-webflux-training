package com.parrino.riccardo.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.demo.model.Order;
import com.parrino.riccardo.demo.repository.OrderRepository;

import reactor.core.publisher.Mono;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    public Mono<Order> placeOrder(Long productId, Integer quantity) {
        return orderRepository.save(
            Order.builder()
                .date(new Date())
                .productId(productId)
                .quantity(quantity)
                .build()
        );
    }

}
