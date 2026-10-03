package com.beefit.dto;

public class MealNutritionResponse {

    private Long mealId;
    private String mealType;

    private double calories;
    private double protein;
    private double carbohydrates;
    private double fat;
    private double fiber;

    public MealNutritionResponse(
            Long mealId,
            String mealType,
            double calories,
            double protein,
            double carbohydrates,
            double fat,
            double fiber) {

        this.mealId = mealId;
        this.mealType = mealType;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.fiber = fiber;
    }

    public Long getMealId() {
        return mealId;
    }

    public String getMealType() {
        return mealType;
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
