package com.thejas.restaurant_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thejas.restaurant_service.model.Restaurant;
import com.thejas.restaurant_service.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin("*")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant r) {
        return ResponseEntity.ok(service.save(r));
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable Long id) {
        Restaurant r = service.getById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }
}