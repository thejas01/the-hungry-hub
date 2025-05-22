package com.thejas.delivery_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thejas.delivery_service.model.Delivery;
import com.thejas.delivery_service.service.DeliveryService;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService service;

    public DeliveryController(DeliveryService service) {
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Delivery>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getDeliveriesByUser(userId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Delivery> updateStatus(@PathVariable Long id, @RequestParam String status) {
        Delivery updated = service.updateStatus(id, status);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}