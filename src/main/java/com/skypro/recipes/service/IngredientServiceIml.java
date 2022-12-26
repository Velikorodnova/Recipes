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
        ingredientMap.put(this.counter++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient get(long id) {
        if (ingredientMap.containsKey(id)) {
            return ingredientMap.get(id);
        } else {
            throw new RuntimeException("Ингредиент не найден!");
        }
    }
}
