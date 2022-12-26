package com.skypro.recipes.controller;

import com.skypro.recipes.model.Recipe;
import com.skypro.recipes.service.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.add(recipe);
    }
    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable("id") long id) {
        return recipeService.get(id);
    }
}
