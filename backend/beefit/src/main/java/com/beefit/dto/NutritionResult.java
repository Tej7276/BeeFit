package com.beefit.dto;

public class NutritionResult {

    private String nutrientName;
    private String unit;
    private double amount;

    public NutritionResult(
            String nutrientName,
            String unit,
            double amount) {

        this.nutrientName = nutrientName;
        this.unit = unit;
        this.amount = amount;
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
