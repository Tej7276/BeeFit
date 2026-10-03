package com.beefit.dto;

import java.util.List;

public class FoodDetailResponse {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private String nutritionBasis;
    private Long categoryId;
    private String categoryName;
    private boolean verified;

    private List<FoodNutrientResponse> nutrients;
    private List<FoodServingResponse> servings;

    public FoodDetailResponse(
            Long id,
            String name,
            String description,
            String brand,
            String nutritionBasis,
            Long categoryId,
            String categoryName,
            boolean verified,
            List<FoodNutrientResponse> nutrients,
            List<FoodServingResponse> servings) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.nutritionBasis = nutritionBasis;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.verified = verified;
        this.nutrients = nutrients;
        this.servings = servings;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public String getNutritionBasis() {
        return nutritionBasis;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean isVerified() {
        return verified;
    }

    public List<FoodNutrientResponse> getNutrients() {
        return nutrients;
    }

    public List<FoodServingResponse> getServings() {
        return servings;
    }
}
