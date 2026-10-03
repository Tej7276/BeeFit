package com.beefit.service;

import com.beefit.dto.DailyNutritionResponse;
import com.beefit.dto.MealNutritionResponse;
import com.beefit.entity.DailyLog;
import com.beefit.entity.Meal;
import com.beefit.repository.DailyLogRepository;
import com.beefit.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DailyNutritionService {

    private final DailyLogRepository dailyLogRepository;
    private final MealRepository mealRepository;
    private final MealNutritionService mealNutritionService;

    public DailyNutritionService(
            DailyLogRepository dailyLogRepository,
            MealRepository mealRepository,
            MealNutritionService mealNutritionService) {

        this.dailyLogRepository = dailyLogRepository;
        this.mealRepository = mealRepository;
        this.mealNutritionService = mealNutritionService;
    }

    public DailyNutritionResponse calculateDailyNutrition(
            LocalDate date) {

        DailyLog dailyLog = dailyLogRepository
                .findByLogDate(date)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Daily log not found for date: " + date
                        ));

        List<Meal> meals =
                mealRepository.findByDailyLogId(dailyLog.getId());

        double calories = 0;
        double protein = 0;
        double carbohydrates = 0;
        double fat = 0;
        double fiber = 0;

        for (Meal meal : meals) {

            MealNutritionResponse nutrition =
                    mealNutritionService.calculateMealNutrition(
                            meal.getId()
                    );

            calories += nutrition.getCalories();
            protein += nutrition.getProtein();
            carbohydrates += nutrition.getCarbohydrates();
            fat += nutrition.getFat();
            fiber += nutrition.getFiber();
        }

        return new DailyNutritionResponse(
                dailyLog.getId(),
                dailyLog.getLogDate().toString(),
                calories,
                protein,
                carbohydrates,
                fat,
                fiber
        );
    }
}
