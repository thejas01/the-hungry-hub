package com.thejas.restaurant_service.service;

import org.springframework.stereotype.Service;

import com.thejas.restaurant_service.model.MenuItem;
import com.thejas.restaurant_service.repository.MenuItemRepository;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository repo;

    public MenuItemService(MenuItemRepository repo) {
        this.repo = repo;
    }

    public MenuItem save(MenuItem item) {
        return repo.save(item);
    }

    public List<MenuItem> getByRestaurant(Long restaurantId) {
        return repo.findByRestaurantId(restaurantId);
    }
}