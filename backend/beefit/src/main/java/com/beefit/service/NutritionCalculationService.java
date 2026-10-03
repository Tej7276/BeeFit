package com.beefit.service;

import com.beefit.dto.NutritionResult;
import com.beefit.entity.Food;
import com.beefit.entity.FoodNutrient;
import com.beefit.repository.FoodNutrientRepository;
import com.beefit.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionCalculationService {

    private final FoodNutrientRepository foodNutrientRepository;
    private final FoodRepository foodRepository;

    public NutritionCalculationService(
            FoodNutrientRepository foodNutrientRepository,
            FoodRepository foodRepository) {

        this.foodNutrientRepository = foodNutrientRepository;
        this.foodRepository = foodRepository;
    }

    public List<NutritionResult> calculateNutrition(
            Long foodId,
            double grams) {

        if (grams <= 0) {
            throw new IllegalArgumentException(
                    "Grams must be greater than 0"
            );
        }

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Food not found: " + foodId
                        ));

        if (!"PER_100G".equals(food.getNutritionBasis())) {
            throw new IllegalArgumentException(
                    "Unsupported nutrition basis: "
                            + food.getNutritionBasis()
            );
        }

        List<FoodNutrient> foodNutrients =
                foodNutrientRepository.findByFoodId(foodId);

        double multiplier = grams / 100.0;

        return foodNutrients.stream()
                .map(foodNutrient -> new NutritionResult(
                        foodNutrient.getNutrient().getName(),
                        foodNutrient.getNutrient().getUnit(),
                        foodNutrient.getAmount() * multiplier
                ))
                .toList();
    }
}
