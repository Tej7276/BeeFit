package com.beefit.controller;

import com.beefit.entity.Meal;
import com.beefit.entity.MealType;
import com.beefit.service.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping
    public Meal createMeal(
            @RequestParam LocalDate date,
            @RequestParam MealType mealType) {

        return mealService.createMeal(date, mealType);
    }

    @GetMapping("/date/{date}")
    public List<Meal> getMealsByDate(
            @PathVariable LocalDate date) {

        return mealService.getMealsByDate(date);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(
            @PathVariable Long id) {

        return mealService.getMealById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/date/{date}/type/{mealType}")
    public ResponseEntity<Meal> getMealByDateAndType(
            @PathVariable LocalDate date,
            @PathVariable MealType mealType) {

        return mealService.getMealByDateAndType(date, mealType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(
            @PathVariable Long id) {

        mealService.deleteMeal(id);

        return ResponseEntity.noContent().build();
    }
}
