package com.skypro.recipes.model;

import java.util.List;

public class Recipe {

    private String name;
    private int timeForPreparing;
    private List<Ingredient> ingredient;
    private List<String> steps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeForPreparing() {
        return timeForPreparing;
    }

    public void setTimeForPreparing(int timeForPreparing) {
        this.timeForPreparing = timeForPreparing;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
