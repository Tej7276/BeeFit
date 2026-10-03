package com.beefit.controller;

import com.beefit.entity.Nutrient;
import com.beefit.service.NutrientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutrients")
public class NutrientController {

    private final NutrientService nutrientService;

    public NutrientController(NutrientService nutrientService) {
        this.nutrientService = nutrientService;
    }

    @PostMapping
    public Nutrient addNutrient(@RequestBody Nutrient nutrient) {
        return nutrientService.addNutrient(nutrient);
    }

    @GetMapping
    public List<Nutrient> getAllNutrients() {
        return nutrientService.getAllNutrients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nutrient> getNutrientById(@PathVariable Long id) {
        return nutrientService.getNutrientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutrient(@PathVariable Long id) {
        nutrientService.deleteNutrient(id);
        return ResponseEntity.noContent().build();
    }
}
