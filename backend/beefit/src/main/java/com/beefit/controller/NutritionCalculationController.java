package com.beefit.controller;

import com.beefit.dto.NutritionResult;
import com.beefit.service.NutritionCalculationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutrition")
public class NutritionCalculationController {

    private final NutritionCalculationService nutritionCalculationService;

    public NutritionCalculationController(
            NutritionCalculationService nutritionCalculationService) {

        this.nutritionCalculationService =
                nutritionCalculationService;
    }

    @GetMapping("/calculate")
    public List<NutritionResult> calculateNutrition(
            @RequestParam Long foodId,
            @RequestParam double grams) {

        return nutritionCalculationService
                .calculateNutrition(foodId, grams);
    }
}
