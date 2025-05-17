package com.thejas.restaurant_service.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference //
    private Restaurant restaurant;
}
// You're encountering a JSON serialization issue in your restaurant-service application. The error indicates an infinite recursion problem in your object model that's causing the JSON serializer to exceed its maximum nesting depth.
// This is a common issue with bidirectional relationships in JPA entities. In your case, the  MenuItem class has a reference to  Restaurant, and likely  Restaurant has a collection of  MenuItem objects, creating a circular reference.
// You'll also need to add @JsonManagedReference to the Restaurant class's collection of menu items. If you don't have access to that file, please share it using @restaurant-service/src/main/java/com/thejas/restaurant_service/model/Restaurant.java.
// The @JsonBackReference annotation breaks the circular reference during JSON serialization by excluding the annotated property from serialization. The @JsonManagedReference is used on the other side of the relationship to indicate the forward part of the reference.