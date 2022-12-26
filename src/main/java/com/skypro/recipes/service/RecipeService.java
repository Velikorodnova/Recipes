package com.skypro.recipes.service;

import com.skypro.recipes.model.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe add(Recipe recipes);

    Recipe get(long id);

    Recipe recipeEditing(long id, Recipe recipe);

    Recipe remove(long id);


    List<Recipe> getAll();
}
