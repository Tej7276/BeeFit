package com.beefit.dto;

public class FoodNutrientResponse {

    private Long id;
    private Long foodId;
    private String foodName;
    private Long nutrientId;
    private String nutrientName;
    private String unit;
    private double amount;

    public FoodNutrientResponse() {
    }

    public FoodNutrientResponse(
            Long id,
            Long foodId,
            String foodName,
            Long nutrientId,
            String nutrientName,
            String unit,
            double amount) {

        this.id = id;
        this.foodId = foodId;
        this.foodName = foodName;
        this.nutrientId = nutrientId;
        this.nutrientName = nutrientName;
        this.unit = unit;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Long getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public Long getNutrientId() {
        return nutrientId;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public String getUnit() {
        return unit;
    }

    public double getAmount() {
        return amount;
    }
}
