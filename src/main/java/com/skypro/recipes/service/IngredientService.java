package com.skypro.recipes.service;

import com.skypro.recipes.model.Ingredient;
public interface IngredientService {
    Ingredient add(Ingredient ingredient);

    Ingredient get(long id);
}
