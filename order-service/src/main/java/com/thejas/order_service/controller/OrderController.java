package com.thejas.order_service.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thejas.order_service.model.Order;
import com.thejas.order_service.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        return ResponseEntity.ok(service.placeOrder(order));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getOrdersByUser(userId));
    }

    @PostMapping("/place/{restaurantId}")
    public ResponseEntity<String> placeOrder(@PathVariable Long restaurantId) {
        service.placeOrder(restaurantId);
        return ResponseEntity.ok("Order placed successfully");
    }
}