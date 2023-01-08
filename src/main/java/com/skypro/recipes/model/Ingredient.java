package com.skypro.recipes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Ingredient {

    private String name;
    private int count;
    private String measure;

    @Override
    public String toString() {
        return "Ингредиент " +
                " Название ингредиента" + name + '\'' +
                ", Количество " + count +
                ", Единица измерения " + measure;
    }
}
