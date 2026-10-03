package com.beefit.service;

import com.beefit.entity.FoodCategory;
import com.beefit.repository.FoodCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    public FoodCategoryService(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public FoodCategory addCategory(FoodCategory category) {
        return foodCategoryRepository.save(category);
    }

    public List<FoodCategory> getAllCategories() {
        return foodCategoryRepository.findAll();
    }

    public Optional<FoodCategory> getCategoryById(Long id) {
        return foodCategoryRepository.findById(id);
    }

    public void deleteCategory(Long id) {
        foodCategoryRepository.deleteById(id);
    }
}
