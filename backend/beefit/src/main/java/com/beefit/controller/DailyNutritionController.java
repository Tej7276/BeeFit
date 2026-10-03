package com.beefit.controller;

import com.beefit.dto.DailyNutritionResponse;
import com.beefit.service.DailyNutritionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/daily-logs")
public class DailyNutritionController {

    private final DailyNutritionService dailyNutritionService;

    public DailyNutritionController(
            DailyNutritionService dailyNutritionService) {

        this.dailyNutritionService =
                dailyNutritionService;
    }

    @GetMapping("/date/{date}/nutrition")
    public DailyNutritionResponse calculateDailyNutrition(
            @PathVariable LocalDate date) {

        return dailyNutritionService
                .calculateDailyNutrition(date);
    }
}
