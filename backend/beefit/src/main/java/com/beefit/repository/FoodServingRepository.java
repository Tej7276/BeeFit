package com.beefit.repository;

import com.beefit.entity.FoodServing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodServingRepository
        extends JpaRepository<FoodServing, Long> {

    List<FoodServing> findByFoodId(Long foodId);
}
