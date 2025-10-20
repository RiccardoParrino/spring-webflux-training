package com.parrino.riccardo.order.service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.models.InventoryRequest;
import com.parrino.riccardo.models.InventoryResponse;

import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@Service
public class OrderService {

    @Autowired
    private final KafkaTemplate<String, InventoryRequest> kafkaTemplate;
    
    private final Map<String, MonoSink<InventoryResponse>> responseSinks = new ConcurrentHashMap<>();
    
    public Mono<Object> checkInventory(InventoryRequest inventoryRequest) {
        return Mono.create( sink -> {
            responseSinks.put(inventoryRequest.getOrderId(), sink);
            kafkaTemplate.send("inventory-request", inventoryRequest.getCollaborationId(), inventoryRequest);
        } ).timeout(Duration.ofSeconds(5))
        .doFinally(sig -> responseSinks.remove(inventoryRequest.getOrderId()));
    }

    @KafkaListener(topics = "inventory-response", groupId = "inventory-request-consumer")
    public void handleResponse(InventoryResponse response) {
        MonoSink<InventoryResponse> sink = responseSinks.remove(response.getOrderId());
        if (sink != null)
            sink.success(response);
    }

}
