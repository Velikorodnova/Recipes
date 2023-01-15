package com.skypro.recipes.service;

import com.skypro.recipes.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface RecipeService {
    Recipe add(Recipe recipes);

    Recipe get(long id);

    Recipe recipeEditing(long id, Recipe recipe);

    Recipe remove(long id);

    List<Recipe> getAll();

    Path createRecipeFile() throws IOException;
}
