package com.thejas.restaurant_service.service;

import org.springframework.stereotype.Service;

import com.thejas.restaurant_service.model.Restaurant;
import com.thejas.restaurant_service.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repo;

    public RestaurantService(RestaurantRepository repo) {
        this.repo = repo;
    }

    public Restaurant save(Restaurant r) {
        return repo.save(r);
    }

    public List<Restaurant> getAll() {
        return repo.findAll();
    }

    public Restaurant getById(Long id) {
        return repo.findById(id).orElse(null);
    }
}