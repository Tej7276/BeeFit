package com.beefit.controller;

import com.beefit.entity.FoodServing;
import com.beefit.service.FoodServingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-servings")
public class FoodServingController {

    private final FoodServingService foodServingService;

    public FoodServingController(FoodServingService foodServingService) {
        this.foodServingService = foodServingService;
    }

    @PostMapping
    public FoodServing addServing(@RequestBody FoodServing serving) {
        return foodServingService.addServing(serving);
    }

    @GetMapping
    public List<FoodServing> getAllServings() {
        return foodServingService.getAllServings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodServing> getServingById(@PathVariable Long id) {
        return foodServingService.getServingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServing(@PathVariable Long id) {
        foodServingService.deleteServing(id);
        return ResponseEntity.noContent().build();
    }
}
