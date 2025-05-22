package com.thejas.delivery_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.thejas.delivery_service.model.Delivery;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByUserId(Long userId);
}