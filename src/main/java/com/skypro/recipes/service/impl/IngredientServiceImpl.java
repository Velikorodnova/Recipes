package com.skypro.recipes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.recipes.model.Ingredient;
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
public class IngredientServiceImpl implements IngredientService {
    private Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private final FileService fileService;
    private Long counter = 0L;

    public IngredientServiceImpl(@Qualifier("fileServiceIngredientImpl") FileService fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        if (ingredientMap.containsKey(ingredientMap.get(ingredient))) {
            throw new AddingError("Ошибка при добавлении элемента!");
        } else {
            ingredientMap.put(this.counter++, ingredient);
        }
        saveToFile();
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
        String json = fileService.readFromFile();
        try {
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Path createIngredientFile() {
        Path path = fileService.createTempFile("ingredient");
        for (Ingredient ingredient : ingredientMap.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append("Наименование ингредиента: ").append(ingredient.getName()).append("\n").append
                        ("Количество: ").append(String.valueOf(ingredient.getCount())).append("\n").append
                        ("Единица измерения: ").append(String.valueOf(ingredient.getMeasure())).append("\n" + "\n");
            } catch (IOException e) {
                throw new FileError("Для создания файла необходимо ввести корректно данные");
            }
        }
        return path;
    }
}
