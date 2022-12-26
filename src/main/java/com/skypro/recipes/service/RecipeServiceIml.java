package com.skypro.recipes.service;

import com.skypro.recipes.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceIml implements RecipeService {
    private final Map<Long, Recipe> recipesMap = new HashMap<>();

    private Long counter = 0L;

    @Override
    public Recipe add(Recipe recipe) {
        recipesMap.put(this.counter++, recipe);
        return recipe;
    }

    @Override
    public Recipe get(long id) {
        if (recipesMap.containsKey(id)) {
            return recipesMap.get(id);
        } else {
            throw new RuntimeException("Рецепт не найден!");
        }
    }
}
