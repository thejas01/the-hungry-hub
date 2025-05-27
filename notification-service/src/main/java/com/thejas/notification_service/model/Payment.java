package com.thejas.notification_service.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Payment {
    private Long id;
    private Long orderId;
    private Long userId;
    private Double amount;
    private String status;
    private LocalDateTime paidAt;
}
