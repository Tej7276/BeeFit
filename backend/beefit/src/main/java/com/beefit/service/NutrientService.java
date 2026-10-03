package com.beefit.service;

import com.beefit.entity.Nutrient;
import com.beefit.repository.NutrientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutrientService {

    private final NutrientRepository nutrientRepository;

    public NutrientService(NutrientRepository nutrientRepository) {
        this.nutrientRepository = nutrientRepository;
    }

    public Nutrient addNutrient(Nutrient nutrient) {
        return nutrientRepository.save(nutrient);
    }

    public List<Nutrient> getAllNutrients() {
        return nutrientRepository.findAll();
    }

    public Optional<Nutrient> getNutrientById(Long id) {
        return nutrientRepository.findById(id);
    }

    public void deleteNutrient(Long id) {
        nutrientRepository.deleteById(id);
    }
}
