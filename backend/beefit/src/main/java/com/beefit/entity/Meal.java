package com.beefit.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "meals",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_daily_log_meal_type",
            columnNames = {"daily_log_id", "meal_type"}
        )
    }
)
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "daily_log_id", nullable = false)
    private DailyLog dailyLog;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;

    public Meal() {}

    public Meal(DailyLog dailyLog, MealType mealType) {
        this.dailyLog = dailyLog;
        this.mealType = mealType;
    }

    public Long getId() {
        return id;
    }

    public DailyLog getDailyLog() {
        return dailyLog;
    }

    public void setDailyLog(DailyLog dailyLog) {
        this.dailyLog = dailyLog;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }
}
