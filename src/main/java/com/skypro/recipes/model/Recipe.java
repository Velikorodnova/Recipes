package com.skypro.recipes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Recipe {
    private String name;
    private int timeForPreparing;
    private List<Ingredient> ingredient;
    private List<String> steps;

    @Override
    public String toString() {
        return "Рецепт " +
                " Название рецепта " + name + '\'' +
                ", Время приготовления " + timeForPreparing +
                ", Ингридиенты " + ingredient +
                ", Шаги приготовления" + steps;
    }
}
