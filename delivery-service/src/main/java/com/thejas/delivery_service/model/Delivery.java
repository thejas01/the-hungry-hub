package com.thejas.delivery_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Long userId;
    private Long restaurantId;

    @ElementCollection
    private List<String> items;

    private String status; // e.g., PENDING, OUT_FOR_DELIVERY, DELIVERED

    private LocalDateTime createdAt;
}