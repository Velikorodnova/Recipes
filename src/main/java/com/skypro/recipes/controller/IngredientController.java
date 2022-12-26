package com.skypro.recipes.controller;

import com.skypro.recipes.model.Ingredient;
import com.skypro.recipes.service.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.add(ingredient);
    }
    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") long id) {
        return ingredientService.get(id);
    }
}
