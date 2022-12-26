package com.skypro.recipes.service;

import com.skypro.recipes.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceIml implements IngredientService {

    private final Map<Long, Ingredient> ingredientMap = new HashMap<>();

    private Long counter = 0L;

    @Override
    public Ingredient add(Ingredient ingredient) {
        try {
            ingredientMap.put(this.counter++, ingredient);
            return ingredient;
        } catch (Exception e) {
            System.out.print("Ингридиент уже существует!");
        }
        return ingredient;
    }


    @Override
    public Ingredient get(long id) {
        return ingredientMap.get(id);
    }
}
