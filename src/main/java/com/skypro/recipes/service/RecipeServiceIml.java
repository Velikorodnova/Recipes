package com.skypro.recipes.service;

import com.skypro.recipes.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipeServiceIml implements RecipeService {
    private final Map<Long, Recipe> recipesMap = new HashMap<>();

    private Long counter = 0L;

    @Override
    public Recipe add(Recipe recipe) {
        if (recipesMap.containsKey(recipesMap.get(recipe))) {
            throw new AddingError("Ошибка при добавлении элемента!");
        } else {
            recipesMap.put(this.counter++, recipe);
        }
        return recipe;
    }

    @Override
    public Recipe get(long id) {
        if (recipesMap.containsKey(id)) {
            return recipesMap.get(id);
        } else {
            throw new ElementNotFound("Рецепт не найден!");
        }
    }

    @Override
    public Recipe recipeEditing(long id, Recipe recipe) {
        if (recipesMap.containsKey(id)) {
            recipesMap.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override

    public Recipe remove(long id) {
        return recipesMap.remove(id);
    }

    @Override
    public List<Recipe> getAll() {
        return new ArrayList<>(this.recipesMap.values());
    }
}
