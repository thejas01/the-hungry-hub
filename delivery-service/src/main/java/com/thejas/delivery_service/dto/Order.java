package com.thejas.delivery_service.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Long userId;
    private Long restaurantId;
    private Double totalAmount;
    private List<String> items;
    private LocalDateTime orderedAt;
}