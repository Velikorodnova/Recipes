package com.skypro.recipes.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ElementNotFound extends RuntimeException {
    public ElementNotFound(String message) {
    }
}
