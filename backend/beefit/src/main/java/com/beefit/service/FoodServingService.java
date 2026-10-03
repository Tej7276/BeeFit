package com.beefit.service;

import com.beefit.dto.FoodServingResponse;
import com.beefit.entity.FoodServing;
import com.beefit.repository.FoodServingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServingService {

    private final FoodServingRepository foodServingRepository;

    public FoodServingService(
            FoodServingRepository foodServingRepository) {

        this.foodServingRepository = foodServingRepository;
    }

    public FoodServing addServing(FoodServing serving) {
        return foodServingRepository.save(serving);
    }

    public List<FoodServing> getAllServings() {
        return foodServingRepository.findAll();
    }

    public Optional<FoodServing> getServingById(Long id) {
        return foodServingRepository.findById(id);
    }

    public void deleteServing(Long id) {
        foodServingRepository.deleteById(id);
    }

    public List<FoodServingResponse> getServingsByFoodId(
            Long foodId) {

        return foodServingRepository.findByFoodId(foodId)
                .stream()
                .map(serving -> new FoodServingResponse(
                        serving.getId(),
                        serving.getFood().getId(),
                        serving.getName(),
                        serving.getAmount(),
                        serving.getUnit(),
                        serving.getGrams()
                ))
                .toList();
    }
}
