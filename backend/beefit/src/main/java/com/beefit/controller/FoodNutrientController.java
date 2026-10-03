package com.beefit.controller;

import com.beefit.dto.FoodNutrientResponse;
import com.beefit.entity.FoodNutrient;
import com.beefit.service.FoodNutrientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-nutrients")
public class FoodNutrientController {

    private final FoodNutrientService foodNutrientService;

    public FoodNutrientController(
            FoodNutrientService foodNutrientService) {

        this.foodNutrientService = foodNutrientService;
    }

    @PostMapping
    public FoodNutrient addFoodNutrient(
            @RequestBody FoodNutrient foodNutrient) {

        return foodNutrientService.addFoodNutrient(foodNutrient);
    }

    @GetMapping
    public List<FoodNutrientResponse> getAllFoodNutrients() {

        return foodNutrientService.getAllFoodNutrients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodNutrientResponse> getFoodNutrientById(
            @PathVariable Long id) {

        return foodNutrientService.getFoodNutrientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodNutrient(
            @PathVariable Long id) {

        foodNutrientService.deleteFoodNutrient(id);

        return ResponseEntity.noContent().build();
    }
}
