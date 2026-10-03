package com.beefit.repository;

import com.beefit.entity.FoodNutrient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodNutrientRepository extends JpaRepository<FoodNutrient, Long> {

    List<FoodNutrient> findByFoodId(Long foodId);
}
