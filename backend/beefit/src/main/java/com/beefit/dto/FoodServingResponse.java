package com.beefit.dto;

public class FoodServingResponse {

    private Long id;
    private Long foodId;
    private String name;
    private double amount;
    private String unit;
    private double grams;

    public FoodServingResponse(
            Long id,
            Long foodId,
            String name,
            double amount,
            String unit,
            double grams) {

        this.id = id;
        this.foodId = foodId;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.grams = grams;
    }

    public Long getId() {
        return id;
    }

    public Long getFoodId() {
        return foodId;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public double getGrams() {
        return grams;
    }
}
