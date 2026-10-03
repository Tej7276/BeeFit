package com.beefit.repository;

import com.beefit.entity.Meal;
import com.beefit.entity.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByDailyLogId(Long dailyLogId);

    Optional<Meal> findByDailyLogIdAndMealType(
            Long dailyLogId,
            MealType mealType
    );
}
