package com.thejas.restaurant_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thejas.restaurant_service.model.MenuItem;
import com.thejas.restaurant_service.model.Restaurant;
import com.thejas.restaurant_service.service.MenuItemService;
import com.thejas.restaurant_service.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menu")
public class MenuItemController {

    private final MenuItemService itemService;
    private final RestaurantService restaurantService;

    public MenuItemController(MenuItemService itemService, RestaurantService restaurantService) {
        this.itemService = itemService;
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<MenuItem> addItem(
            @PathVariable Long restaurantId,
            @RequestBody MenuItem item
    ) {
        Restaurant r = restaurantService.getById(restaurantId);
        if (r == null) return ResponseEntity.notFound().build();

        item.setRestaurant(r);
        return ResponseEntity.ok(itemService.save(item));
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getMenu(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(itemService.getByRestaurant(restaurantId));
    }
}
