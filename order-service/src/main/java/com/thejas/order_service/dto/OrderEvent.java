package com.thejas.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEvent {
    private Long orderId;
    private Long userId;
    private Long restaurantId;
    private Double totalAmount;
    private String status;
    private String deliveryAddress;
    private LocalDateTime orderedAt;
}