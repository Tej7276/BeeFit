package com.beefit.controller;

import com.beefit.dto.MealNutritionResponse;
import com.beefit.service.MealNutritionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
public class MealNutritionController {

    private final MealNutritionService mealNutritionService;

    public MealNutritionController(
            MealNutritionService mealNutritionService) {

        this.mealNutritionService = mealNutritionService;
    }

    @GetMapping("/{mealId}/nutrition")
    public MealNutritionResponse calculateMealNutrition(
            @PathVariable Long mealId) {

        return mealNutritionService
                .calculateMealNutrition(mealId);
    }
}
