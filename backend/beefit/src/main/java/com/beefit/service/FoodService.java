package com.beefit.service;

import com.beefit.dto.FoodDetailResponse;
import com.beefit.dto.FoodResponse;
import com.beefit.dto.FoodServingResponse;
import com.beefit.dto.FoodNutrientResponse;
import com.beefit.entity.Food;
import com.beefit.entity.FoodNutrient;
import com.beefit.repository.FoodNutrientRepository;
import com.beefit.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodNutrientRepository foodNutrientRepository;
    private final FoodServingService foodServingService;

    public FoodService(
            FoodRepository foodRepository,
            FoodNutrientRepository foodNutrientRepository,
            FoodServingService foodServingService) {

        this.foodRepository = foodRepository;
        this.foodNutrientRepository = foodNutrientRepository;
        this.foodServingService = foodServingService;
    }

    public Food addFood(Food food) {

        if (food.getName() == null ||
                food.getName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Food name cannot be blank"
            );
        }

        if (food.getNutritionBasis() == null ||
                !food.getNutritionBasis().equals("PER_100G")) {

            throw new IllegalArgumentException(
                    "Nutrition basis must be PER_100G"
            );
        }

        return foodRepository.save(food);
    }

    public List<FoodResponse> searchFoods(String name) {

        return foodRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(food -> new FoodResponse(
                        food.getId(),
                        food.getName(),
                        food.getDescription(),
                        food.getBrand(),
                        food.getCategory() != null
                                ? food.getCategory().getId()
                                : null,
                        food.getCategory() != null
                                ? food.getCategory().getName()
                                : null,
                        food.isVerified()
                ))
                .toList();
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    public Optional<FoodDetailResponse> getFoodDetails(Long id) {

        return foodRepository.findById(id)
                .map(food -> {

                    List<FoodNutrientResponse> nutrients =
                            foodNutrientRepository.findByFoodId(id)
                                    .stream()
                                    .map(this::convertNutrientToResponse)
                                    .toList();

                    List<FoodServingResponse> servings =
                            foodServingService
                                    .getServingsByFoodId(id);

                    return new FoodDetailResponse(
                            food.getId(),
                            food.getName(),
                            food.getDescription(),
                            food.getBrand(),
                            food.getNutritionBasis(),
                            food.getCategory() != null
                                    ? food.getCategory().getId()
                                    : null,
                            food.getCategory() != null
                                    ? food.getCategory().getName()
                                    : null,
                            food.isVerified(),
                            nutrients,
                            servings
                    );
                });
    }

    private FoodNutrientResponse convertNutrientToResponse(
            FoodNutrient foodNutrient) {

        return new FoodNutrientResponse(
                foodNutrient.getId(),
                foodNutrient.getFood().getId(),
                foodNutrient.getFood().getName(),
                foodNutrient.getNutrient().getId(),
                foodNutrient.getNutrient().getName(),
                foodNutrient.getNutrient().getUnit(),
                foodNutrient.getAmount()
        );
    }
}
