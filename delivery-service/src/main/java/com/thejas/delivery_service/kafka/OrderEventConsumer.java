package com.thejas.delivery_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.thejas.delivery_service.model.Delivery;
import com.thejas.delivery_service.repository.DeliveryRepository;
import com.thejas.delivery_service.dto.Order;

import java.time.LocalDateTime;


@Component
public class OrderEventConsumer {

    private final DeliveryRepository repo;

    public OrderEventConsumer(DeliveryRepository repo) {
        this.repo = repo;
    }

    @KafkaListener(topics = "order-events", groupId = "delivery-service-group")
    public void consumeOrderEvent(Order order) {
        try {
            Delivery delivery = Delivery.builder()
                    .orderId(order.getId())
                    .userId(order.getUserId())
                    .restaurantId(order.getRestaurantId())
                    .items(order.getItems())
                    .status("PENDING")
                    .createdAt(LocalDateTime.now())
                    .build();

            repo.save(delivery);
            System.out.println("🚚 Delivery created for Order ID: " + delivery.getOrderId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
