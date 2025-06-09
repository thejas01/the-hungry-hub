package com.thejas.order_service.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {
    
    private Long userId;
    private Long restaurantId;
    private Double totalAmount;
    private String status;
    private String deliveryAddress;
    private List<OrderItemRequest> items;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderItemRequest {
        private Long menuItemId;
        private Integer quantity;
        private Double price;
    }
}