package com.thejas.restaurant_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thejas.restaurant_service.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}