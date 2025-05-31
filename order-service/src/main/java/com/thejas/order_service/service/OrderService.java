package com.thejas.order_service.service;

import java.time.LocalDateTime;
import java.util.List;

import com.thejas.order_service.dto.Restaurant;
import com.thejas.order_service.feign.RestaurantClient;
import com.thejas.order_service.kafka.OrderEventProducer;
import com.thejas.order_service.model.Order;
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

    public List<Order> getOrdersByUser(Long userId) {
        return repo.findByUserId(userId);
    }


}