package com.thejas.order_service.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.thejas.order_service.dto.CreateOrderRequest;
import com.thejas.order_service.dto.Restaurant;
import com.thejas.order_service.feign.RestaurantClient;
import com.thejas.order_service.kafka.OrderEventProducer;
import com.thejas.order_service.model.Order;
import com.thejas.order_service.model.OrderItem;
import com.thejas.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final OrderEventProducer kafkaProducer;

     private final RestaurantClient restaurantClient;

    // public OrderService(RestaurantClient restaurantClient) {
    //     this.restaurantClient = restaurantClient;
    // }
    public List<Order> getAllOrders() {
        return repo.findAll();
    }


    public OrderService(OrderRepository repo, OrderEventProducer kafkaProducer,RestaurantClient restaurantClient) {
        this.repo = repo;
        this.kafkaProducer = kafkaProducer;
        this.restaurantClient = restaurantClient;
    }

    public void placeOrder(Long restaurantId) {
        Restaurant restaurant = restaurantClient.getRestaurantById(restaurantId);
        System.out.println("Ordering from: " + restaurant.getName());
    }

   

    public Order placeOrder(Order order) {
        order.setOrderedAt(LocalDateTime.now());
        Order savedOrder = repo.save(order);
        kafkaProducer.sendOrderEvent(savedOrder); // send to Kafka
        return savedOrder;
    }

    public Order createOrder(CreateOrderRequest request) {
        Order order = Order.builder()
                .userId(request.getUserId())
                .restaurantId(request.getRestaurantId())
                .totalAmount(request.getTotalAmount())
                .status(request.getStatus() != null ? request.getStatus() : "PENDING")
                .deliveryAddress(request.getDeliveryAddress())
                .orderedAt(LocalDateTime.now())
                .build();

        // Create order items
        for (CreateOrderRequest.OrderItemRequest itemRequest : request.getItems()) {
            OrderItem orderItem = OrderItem.builder()
                    .menuItemId(itemRequest.getMenuItemId())
                    .quantity(itemRequest.getQuantity())
                    .price(itemRequest.getPrice())
                    .order(order)
                    .build();
            order.getItems().add(orderItem);
        }

        Order savedOrder = repo.save(order);
        kafkaProducer.sendOrderEvent(savedOrder); // send to Kafka
        return savedOrder;
    }

    public List<Order> getOrdersByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    public Optional<Order> getOrderById(Long id) {
        return repo.findById(id);
    }

    public Order updateOrderStatus(Long id, String status) {
        Optional<Order> orderOpt = repo.findById(id);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(status);
            return repo.save(order);
        }
        throw new RuntimeException("Order not found with id: " + id);
    }

    public Order cancelOrder(Long id) {
        return updateOrderStatus(id, "CANCELLED");
    }

}