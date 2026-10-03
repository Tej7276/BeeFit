package com.beefit.dto;

import com.beefit.entity.MealType;

public class MealItemResponse {

    private Long id;
    private Long mealId;
    private MealType mealType;
    private Long foodId;
    private String foodName;
    private double grams;

    public MealItemResponse(
            Long id,
            Long mealId,
            MealType mealType,
            Long foodId,
            String foodName,
            double grams) {

        this.id = id;
        this.mealId = mealId;
        this.mealType = mealType;
        this.foodId = foodId;
        this.foodName = foodName;
        this.grams = grams;
    }

    public Long getId() {
        return id;
    }

    public Long getMealId() {
        return mealId;
    }

    public MealType getMealType() {
        return mealType;
    }

    public Long getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getGrams() {
        return grams;
    }
}
