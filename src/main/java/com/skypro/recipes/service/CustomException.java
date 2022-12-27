package com.skypro.recipes.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CustomException extends RuntimeException {
    private final Long counter = 1L;;

    public CustomException(int id) {
        super("Рецепт не найден " + id + " !");
    }
}
