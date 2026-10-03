package com.beefit.service;

import com.beefit.dto.FoodNutrientResponse;
import com.beefit.entity.FoodNutrient;
import com.beefit.repository.FoodNutrientRepository;
import com.beefit.repository.FoodRepository;
import com.beefit.repository.NutrientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodNutrientService {

    private final FoodNutrientRepository foodNutrientRepository;
    private final FoodRepository foodRepository;
    private final NutrientRepository nutrientRepository;

    public FoodNutrientService(
            FoodNutrientRepository foodNutrientRepository,
            FoodRepository foodRepository,
            NutrientRepository nutrientRepository) {

        this.foodNutrientRepository = foodNutrientRepository;
        this.foodRepository = foodRepository;
        this.nutrientRepository = nutrientRepository;
    }

    public FoodNutrient addFoodNutrient(
            FoodNutrient foodNutrient) {

        if (foodNutrient.getAmount() < 0) {
            throw new IllegalArgumentException(
                    "Nutrient amount cannot be negative"
            );
        }

        if (foodNutrient.getFood() == null ||
                foodNutrient.getFood().getId() == null) {

            throw new IllegalArgumentException(
                    "Food is required"
            );
        }

        Long foodId = foodNutrient.getFood().getId();

        if (!foodRepository.existsById(foodId)) {
            throw new IllegalArgumentException(
                    "Food not found: " + foodId
            );
        }

        if (foodNutrient.getNutrient() == null ||
                foodNutrient.getNutrient().getId() == null) {

            throw new IllegalArgumentException(
                    "Nutrient is required"
            );
        }

        Long nutrientId = foodNutrient.getNutrient().getId();

        if (!nutrientRepository.existsById(nutrientId)) {
            throw new IllegalArgumentException(
                    "Nutrient not found: " + nutrientId
            );
        }

        return foodNutrientRepository.save(foodNutrient);
    }

    public List<FoodNutrientResponse> getAllFoodNutrients() {

        return foodNutrientRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public Optional<FoodNutrientResponse> getFoodNutrientById(
            Long id) {

        return foodNutrientRepository.findById(id)
                .map(this::convertToResponse);
    }

    public void deleteFoodNutrient(Long id) {
        foodNutrientRepository.deleteById(id);
    }

    private FoodNutrientResponse convertToResponse(
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
