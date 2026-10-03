package com.beefit.service;

import com.beefit.entity.DailyLog;
import com.beefit.entity.Meal;
import com.beefit.entity.MealType;
import com.beefit.repository.DailyLogRepository;
import com.beefit.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final DailyLogRepository dailyLogRepository;

    public MealService(
            MealRepository mealRepository,
            DailyLogRepository dailyLogRepository) {

        this.mealRepository = mealRepository;
        this.dailyLogRepository = dailyLogRepository;
    }

    public Meal createMeal(LocalDate date, MealType mealType) {

        DailyLog dailyLog = dailyLogRepository
                .findByLogDate(date)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Daily log not found for date: " + date
                        ));

        Optional<Meal> existingMeal =
                mealRepository.findByDailyLogIdAndMealType(
                        dailyLog.getId(),
                        mealType
                );

        if (existingMeal.isPresent()) {
            return existingMeal.get();
        }

        Meal meal = new Meal(dailyLog, mealType);

        return mealRepository.save(meal);
    }

    public List<Meal> getMealsByDate(LocalDate date) {

        DailyLog dailyLog = dailyLogRepository
                .findByLogDate(date)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Daily log not found for date: " + date
                        ));

        return mealRepository.findByDailyLogId(dailyLog.getId());
    }

    public Optional<Meal> getMealById(Long id) {
        return mealRepository.findById(id);
    }

    public Optional<Meal> getMealByDateAndType(
            LocalDate date,
            MealType mealType) {

        return dailyLogRepository.findByLogDate(date)
                .flatMap(dailyLog ->
                        mealRepository.findByDailyLogIdAndMealType(
                                dailyLog.getId(),
                                mealType
                        ));
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }
}
