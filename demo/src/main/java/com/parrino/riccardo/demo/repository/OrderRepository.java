package com.parrino.riccardo.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.parrino.riccardo.demo.model.Order;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long>{
    
}
