package com.skypro.recipes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipes.model.Recipe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipeServiceIml implements RecipeService {
    private Map<Long, Recipe> recipesMap = new HashMap<>();
    private final FileService fileService;

    private Long counter = 0L;

    public RecipeServiceIml(@Qualifier("fileServiceRecipeImpl") FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public Recipe add(Recipe recipe) {
        if (recipesMap.containsKey(recipesMap.get(recipe))) {
            throw new AddingError("Ошибка при добавлении элемента!");
        } else {
            recipesMap.put(this.counter++, recipe);
        }
        saveToFile();
        return recipe;
    }

    @Override
    public Recipe get(long id) {
        if (recipesMap.containsKey(id)) {
            return recipesMap.get(id);
        } else {
            throw new ElementNotFound("Рецепт не найден!");
        }}

    @Override
    public Recipe recipeEditing(long id, Recipe recipe) {
        if (StringUtils.isBlank(recipe.getName())) {
            throw new EmptyError("Необходимо полностью заполнить поля");
        }
        if (StringUtils.isAllEmpty(recipe.getName())) {
            throw new EmptyError("Необходимо полностью заполнить поля");
        }
        if (recipesMap.containsKey(id)) {
            recipesMap.put(id, recipe);
            saveToFile();
            return recipe;
    }
        return recipe;
    }

//    @Override
//    public Recipe recipeEditing(long id, Recipe recipe) {
//        recipesMap.put(id, recipe);
//        if (recipesMap.containsKey(id) & (StringUtils.isAllEmpty((CharSequence) recipesMap.get(recipe)) &
//                (StringUtils.isBlank((CharSequence) recipesMap.get(recipe))))) {
//            throw new EmptyError("Необходимо полностью заполнить поля");
//        }
//        saveToFile();
//        return recipe;
//    }

    @Override
    public Recipe remove(long id) {
        return recipesMap.remove(id);
    }

    @Override
    public List<Recipe> getAll() {
        return new ArrayList<>(this.recipesMap.values());
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipesMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = fileService.readFromFile();
            recipesMap = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
