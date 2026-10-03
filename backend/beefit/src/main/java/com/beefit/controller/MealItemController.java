package com.beefit.controller;

import com.beefit.dto.MealItemResponse;
import com.beefit.dto.NutritionResult;
import com.beefit.service.MealItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal-items")
public class MealItemController {

    private final MealItemService mealItemService;

    public MealItemController(MealItemService mealItemService) {
        this.mealItemService = mealItemService;
    }

    @PostMapping
    public MealItemResponse addMealItem(
            @RequestParam Long mealId,
            @RequestParam Long foodId,
            @RequestParam double grams) {

        return mealItemService.addMealItem(
                mealId,
                foodId,
                grams
        );
    }

    @GetMapping("/meal/{mealId}")
    public List<MealItemResponse> getItemsByMealId(
            @PathVariable Long mealId) {

        return mealItemService.getItemsByMealId(mealId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealItemResponse> getMealItemById(
            @PathVariable Long id) {

        return mealItemService.getMealItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/nutrition")
    public List<NutritionResult> calculateNutritionForMealItem(
            @PathVariable Long id) {

        return mealItemService.calculateNutritionForMealItem(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealItem(
            @PathVariable Long id) {

        mealItemService.deleteMealItem(id);

        return ResponseEntity.noContent().build();
    }
}
