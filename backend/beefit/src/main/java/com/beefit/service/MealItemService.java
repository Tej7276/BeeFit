package com.beefit.service;

import com.beefit.dto.MealItemResponse;
import com.beefit.dto.NutritionResult;
import com.beefit.entity.Food;
import com.beefit.entity.Meal;
import com.beefit.entity.MealItem;
import com.beefit.repository.FoodRepository;
import com.beefit.repository.MealItemRepository;
import com.beefit.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealItemService {

    private final MealItemRepository mealItemRepository;
    private final MealRepository mealRepository;
    private final FoodRepository foodRepository;
    private final NutritionCalculationService nutritionCalculationService;

    public MealItemService(
            MealItemRepository mealItemRepository,
            MealRepository mealRepository,
            FoodRepository foodRepository,
            NutritionCalculationService nutritionCalculationService) {

        this.mealItemRepository = mealItemRepository;
        this.mealRepository = mealRepository;
        this.foodRepository = foodRepository;
        this.nutritionCalculationService =
                nutritionCalculationService;
    }

    public MealItemResponse addMealItem(
            Long mealId,
            Long foodId,
            double grams) {

        if (grams <= 0) {
            throw new IllegalArgumentException(
                    "Grams must be greater than 0"
            );
        }

        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Meal not found: " + mealId
                        ));

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Food not found: " + foodId
                        ));

        MealItem mealItem =
                new MealItem(meal, food, grams);

        MealItem savedItem =
                mealItemRepository.save(mealItem);

        return convertToResponse(savedItem);
    }

    public List<MealItemResponse> getItemsByMealId(Long mealId) {

        return mealItemRepository.findByMealId(mealId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public Optional<MealItemResponse> getMealItemById(Long id) {

        return mealItemRepository.findById(id)
                .map(this::convertToResponse);
    }

    public List<NutritionResult> calculateNutritionForMealItem(
            Long id) {

        MealItem mealItem = mealItemRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Meal item not found: " + id
                        ));

        return nutritionCalculationService.calculateNutrition(
                mealItem.getFood().getId(),
                mealItem.getGrams()
        );
    }

    public void deleteMealItem(Long id) {
        mealItemRepository.deleteById(id);
    }

    private MealItemResponse convertToResponse(
            MealItem mealItem) {

        return new MealItemResponse(
                mealItem.getId(),
                mealItem.getMeal().getId(),
                mealItem.getMeal().getMealType(),
                mealItem.getFood().getId(),
                mealItem.getFood().getName(),
                mealItem.getGrams()
        );
    }
}
