package com.beefit.controller;

import com.beefit.entity.FoodCategory;
import com.beefit.service.FoodCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class FoodCategoryController {

    private final FoodCategoryService foodCategoryService;

    public FoodCategoryController(FoodCategoryService foodCategoryService) {
        this.foodCategoryService = foodCategoryService;
    }

    @PostMapping
    public FoodCategory addCategory(@RequestBody FoodCategory category) {
        return foodCategoryService.addCategory(category);
    }

    @GetMapping
    public List<FoodCategory> getAllCategories() {
        return foodCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodCategory> getCategoryById(@PathVariable Long id) {
        return foodCategoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        foodCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
