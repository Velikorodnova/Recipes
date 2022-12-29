package com.skypro.recipes.service;

import com.skypro.recipes.model.Ingredient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IngredientServiceIml implements IngredientService {
    private final Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private Long counter = 0L;
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
    public Ingredient inredientEditing(long id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id) || (!StringUtils.isAllEmpty((CharSequence) ingredientMap.get(ingredient)) ||
        (!StringUtils.isBlank ((CharSequence)ingredientMap.get(ingredient))))) {
            ingredientMap.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public Ingredient remove(long id) {
        return ingredientMap.remove(id);
    }

    @Override
    public List<Ingredient> getAll() {
        return new ArrayList<>(this.ingredientMap.values());
    }
}
