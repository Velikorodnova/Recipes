package com.skypro.recipes.controller;
import com.skypro.recipes.model.Ingredient;
import com.skypro.recipes.service.IngredientService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @PutMapping("/{id}")
    public Ingredient ingredientEditing(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        return ingredientService.inredientEditing(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public Ingredient removeRecipe(@PathVariable("id") long id) {
        return ingredientService.remove(id);
    }

    @GetMapping
    public List<Ingredient> getAll() {
        return this.ingredientService.getAll();
    }
}
