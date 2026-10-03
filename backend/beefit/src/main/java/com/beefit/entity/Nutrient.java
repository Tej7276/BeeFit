package com.beefit.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nutrients")
public class Nutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String unit;

    @Column(name = "nutrient_type", nullable = false)
    private String nutrientType;

    public Nutrient() {
    }

    public Nutrient(String name, String code, String unit, String nutrientType) {
        this.name = name;
        this.code = code;
        this.unit = unit;
        this.nutrientType = nutrientType;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNutrientType() {
        return nutrientType;
    }

    public void setNutrientType(String nutrientType) {
        this.nutrientType = nutrientType;
    }
}
