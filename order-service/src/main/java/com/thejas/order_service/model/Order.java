package com.thejas.order_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "`order`") // Escape the reserved keyword with backticks
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long restaurantId;
    private Double totalAmount;

    @ElementCollection
    private List<String> items;

    private LocalDateTime orderedAt;
}
