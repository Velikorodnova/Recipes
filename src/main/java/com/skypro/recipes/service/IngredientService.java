package com.skypro.recipes.service;

import com.skypro.recipes.model.Ingredient;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface IngredientService {
    Ingredient add(Ingredient ingredient);

    Ingredient get(long id);

    Ingredient ingredientEditing(long id, Ingredient ingredient);

    Ingredient remove(long id);

    List<Ingredient> getAll();

    Path createIngredientFile() throws IOException;
}
