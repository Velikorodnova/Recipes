package com.skypro.recipes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipes.model.Ingredient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IngredientServiceIml implements IngredientService {
    private Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private final FileService fileService;
    private Long counter = 0L;

    public IngredientServiceIml(@Qualifier("fileServiceIngredientImpl") FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        if (ingredientMap.containsKey(ingredientMap.get(ingredient))) {
            throw new AddingError("Ошибка при добавлении элемента!");
        } else {
            ingredientMap.put(this.counter++, ingredient);
        }
        return ingredient;
    }

    @Override
    public Ingredient get(long id) {
        if (ingredientMap.containsKey(id)) {
            return ingredientMap.get(id);
        } else {
            throw new ElementNotFound("Ингредиент не найден!");
        }
    }

    @Override
    public Ingredient ingredientEditing(long id, Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getName())) {
            throw new EmptyError("Необходимо полностью заполнить поля");
        }
        if (StringUtils.isAllEmpty(ingredient.getName())) {
            throw new EmptyError("Необходимо полностью заполнить поля");
        }
        if (ingredientMap.containsKey(id)) {
            ingredientMap.put(id, ingredient);
            saveToFile();
            return ingredient;
        }
        return ingredient;
    }

    @Override
    public Ingredient remove(long id) {
        return ingredientMap.remove(id);
    }

    @Override
    public List<Ingredient> getAll() {
        return new ArrayList<>(this.ingredientMap.values());
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = fileService.readFromFile();
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
