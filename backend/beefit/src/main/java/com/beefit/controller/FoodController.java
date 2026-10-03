package com.beefit.controller;

import com.beefit.dto.FoodDetailResponse;
import com.beefit.dto.FoodResponse;
import com.beefit.entity.Food;
import com.beefit.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public Food addFood(@RequestBody Food food) {
        return foodService.addFood(food);
    }

    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/search")
    public List<FoodResponse> searchFoods(
            @RequestParam String name) {

        return foodService.searchFoods(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(
            @PathVariable Long id) {

        return foodService.getFoodById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<FoodDetailResponse> getFoodDetails(
            @PathVariable Long id) {

        return foodService.getFoodDetails(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(
            @PathVariable Long id) {

        foodService.deleteFood(id);

        return ResponseEntity.noContent().build();
    }
}
