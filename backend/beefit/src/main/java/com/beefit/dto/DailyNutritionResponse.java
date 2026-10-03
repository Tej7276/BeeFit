package com.beefit.dto;

public class DailyNutritionResponse {

    private Long dailyLogId;
    private String date;

    private double calories;
    private double protein;
    private double carbohydrates;
    private double fat;
    private double fiber;

    public DailyNutritionResponse(
            Long dailyLogId,
            String date,
            double calories,
            double protein,
            double carbohydrates,
            double fat,
            double fiber) {

        this.dailyLogId = dailyLogId;
        this.date = date;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.fiber = fiber;
    }

    public Long getDailyLogId() {
        return dailyLogId;
    }

    public String getDate() {
        return date;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getFat() {
        return fat;
    }

    public double getFiber() {
        return fiber;
    }
}
