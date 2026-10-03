package com.beefit.dto;

public class FoodResponse {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private Long categoryId;
    private String categoryName;
    private boolean verified;

    public FoodResponse(
            Long id,
            String name,
            String description,
            String brand,
            Long categoryId,
            String categoryName,
            boolean verified) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.verified = verified;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean isVerified() {
        return verified;
    }
}
