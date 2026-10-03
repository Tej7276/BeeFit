package com.beefit.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    private String brand;
    @Column(name = "nutrition_basis", nullable = false)
    private String nutritionBasis = "PER_100G";

    @ManyToOne
    @JoinColumn(name = "category_id")
    private FoodCategory category;

    @Column(name = "is_verified", nullable = false)
    private boolean verified = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "food")
    private List<FoodNutrient> nutrients = new ArrayList<>();

    @OneToMany(mappedBy = "food")
    private List<FoodServing> servings = new ArrayList<>();

    public Food() {
    }

    public Food(String name, String description, String brand) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public FoodCategory getCategory() {
        return category;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    public String getNutritionBasis() {
    return nutritionBasis;
    }

    public void setNutritionBasis(String nutritionBasis) {
    this.nutritionBasis = nutritionBasis;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<FoodNutrient> getNutrients() {
        return nutrients;
    }

    public List<FoodServing> getServings() {
        return servings;
    }
}
