package com.thejas.order_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "`order`") // Escape the reserved keyword with backticks
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "items")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long restaurantId;
    private Double totalAmount;
    @Builder.Default
    private String status = "PENDING";
    private String deliveryAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    @Column(name = "ordered_at")
    private LocalDateTime orderedAt;
    
    // Add orderDate for frontend compatibility
    public String getOrderDate() {
        return orderedAt != null ? orderedAt.toString() : null;
    }
}

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.LocalDateTime;
// import java.util.List;

// @Entity
// @Table(name = "`order`") // Escape the reserved keyword with backticks
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class Order {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long userId;
//     private Long restaurantId;
//     private Double totalAmount;

//     @ElementCollection
//     private List<String> items;

//     private LocalDateTime orderedAt;
// }
