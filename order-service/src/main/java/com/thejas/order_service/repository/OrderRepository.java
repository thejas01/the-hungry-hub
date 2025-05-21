package com.thejas.order_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.thejas.order_service.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}