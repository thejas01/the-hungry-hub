package com.thejas.order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.thejas.order_service.dto.Restaurant;

@FeignClient(name = "restaurant-service")
public interface RestaurantClient {

    @GetMapping("/restaurants/{id}")
    Restaurant getRestaurantById(@PathVariable("id") Long id);
}
