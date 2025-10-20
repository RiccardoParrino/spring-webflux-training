package com.parrino.riccardo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String collaborationId;
}
