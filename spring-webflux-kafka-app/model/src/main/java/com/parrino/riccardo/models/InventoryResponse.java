package com.parrino.riccardo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private boolean available;
    private String collaborationId;
}
