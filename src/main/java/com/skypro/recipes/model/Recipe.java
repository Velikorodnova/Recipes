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
}
