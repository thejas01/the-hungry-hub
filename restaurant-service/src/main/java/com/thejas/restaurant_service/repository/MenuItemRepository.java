package com.thejas.restaurant_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thejas.restaurant_service.model.MenuItem;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByRestaurantId(Long restaurantId);
}
