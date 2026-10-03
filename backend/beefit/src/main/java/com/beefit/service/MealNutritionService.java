package com.beefit.service;

import com.beefit.dto.MealNutritionResponse;
import com.beefit.dto.NutritionResult;
import com.beefit.entity.Meal;
import com.beefit.entity.MealItem;
import com.beefit.repository.MealItemRepository;
import com.beefit.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealNutritionService {

    private final MealRepository mealRepository;
    private final MealItemRepository mealItemRepository;
    private final NutritionCalculationService nutritionCalculationService;

    public MealNutritionService(
            MealRepository mealRepository,
            MealItemRepository mealItemRepository,
            NutritionCalculationService nutritionCalculationService) {

        this.mealRepository = mealRepository;
        this.mealItemRepository = mealItemRepository;
        this.nutritionCalculationService =
                nutritionCalculationService;
    }

    public MealNutritionResponse calculateMealNutrition(Long mealId) {

        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Meal not found: " + mealId
                        ));

        List<MealItem> mealItems =
                mealItemRepository.findByMealId(mealId);

        double calories = 0;
        double protein = 0;
        double carbohydrates = 0;
        double fat = 0;
        double fiber = 0;

        for (MealItem mealItem : mealItems) {

            List<NutritionResult> nutrition =
                    nutritionCalculationService.calculateNutrition(
                            mealItem.getFood().getId(),
                            mealItem.getGrams()
                    );

            for (NutritionResult result : nutrition) {

                switch (result.getNutrientName()) {

                    case "Energy" ->
                            calories += result.getAmount();

                    case "Protein" ->
                            protein += result.getAmount();

                    case "Carbohydrate" ->
                            carbohydrates += result.getAmount();

                    case "Fat" ->
                            fat += result.getAmount();

                    case "Fiber" ->
                            fiber += result.getAmount();
                }
            }
        }

        return new MealNutritionResponse(
                meal.getId(),
                meal.getMealType().name(),
                calories,
                protein,
                carbohydrates,
                fat,
                fiber
        );
    }
}
