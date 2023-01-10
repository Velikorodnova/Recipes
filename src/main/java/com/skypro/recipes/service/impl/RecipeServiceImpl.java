package com.skypro.recipes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipes.model.Recipe;
import com.skypro.recipes.service.*;
import com.skypro.recipes.service.exception.AddingError;
import com.skypro.recipes.service.exception.ElementNotFound;
import com.skypro.recipes.service.exception.EmptyError;
import com.skypro.recipes.service.exception.FileError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Map<Long, Recipe> recipesMap = new HashMap<>();
    private final FileService fileService;

    private Long counter = 0L;

    public RecipeServiceImpl(@Qualifier("fileServiceRecipeImpl") FileService fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
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
        }
    }

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

    @Override
    public Recipe remove(long id) {
        return recipesMap.remove(id);
    }

    @Override
    public List<Recipe> getAll() {
        return List.of();
//        return new ArrayList<>(this.recipesMap.values());
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipesMap);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FileError("Не удалось сохранить в файл");
        }
    }

    private void readFromFile() {
        String json = fileService.readFromFile();
        try {
            recipesMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FileError("Не удалось прочитать из файла");
        }
    }

    @Override
    public Path createRecipeFile(){
        Path path = fileService.createTempFile("recipe");
        for (Recipe recipe : recipesMap.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append("Наименование рецепта: ").append(recipe.getName()).append("\n").append
                        ("Время приготовления: ").append(String.valueOf(recipe.getTimeForPreparing())).append("\n").append
                        ("Шаги: ").append(String.valueOf(recipe.getSteps())).append("\n" + "\n");
            } catch (IOException e) {
                e.printStackTrace();
                throw new FileError("Для создания файла необходимо ввести корректно данные");
            }
        }
        return path;
    }
}
