package com.parrino.riccardo.demo.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.demo.model.Order;
import com.parrino.riccardo.demo.repository.OrderRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    public Mono<Order> placeOrder(Long productId, Integer quantity) {
        return orderRepository.save(
            Order.builder()
                .date(LocalDateTime.now())
                .productId(productId)
                .quantity(quantity)
                .build()
        );
    }

    public Flux<Order> findAll() {
        return orderRepository.findAll();
    }

    public Mono<Void> deleteOrder(Long orderId) {
        return orderRepository.deleteById(orderId);
    }
}
