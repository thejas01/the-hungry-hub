package com.thejas.delivery_service.service;

import org.springframework.stereotype.Service;

import com.thejas.delivery_service.model.Delivery;
import com.thejas.delivery_service.repository.DeliveryRepository;

import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository repo;

    public DeliveryService(DeliveryRepository repo) {
        this.repo = repo;
    }

    public List<Delivery> getDeliveriesByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    public Delivery updateStatus(Long id, String status) {
        Delivery delivery = repo.findById(id).orElse(null);
        if (delivery == null) return null;
        delivery.setStatus(status);
        return repo.save(delivery);
    }
}