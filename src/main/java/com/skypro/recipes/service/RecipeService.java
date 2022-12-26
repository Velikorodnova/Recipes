package com.skypro.recipes.service;

import com.skypro.recipes.model.Recipe;

public interface RecipeService {
    Recipe add(Recipe recipes);

    Recipe get(long id);
}
