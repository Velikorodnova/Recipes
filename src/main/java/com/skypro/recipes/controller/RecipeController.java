package com.skypro.recipes.controller;

import com.skypro.recipes.model.Recipe;
import com.skypro.recipes.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD-операции для работы с рецептами")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(summary = "Добавление рецепта")
    @Parameters(value ={
            @Parameter(name = "Название рецепта", example = "Плов")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))
                    })
    })
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.add(recipe);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение рецепта по id")
    @Parameters(value ={
            @Parameter(name = "Название рецепта", example = "Плов")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))
                    })
    })
    public Recipe getRecipe(@PathVariable("id") long id) {
        return recipeService.get(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование рецепта")
    @Parameters(value ={
            @Parameter(name = "Название рецепта", example = "Плов")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт отредактирован",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))
                    })
    })
    public Recipe recipeEditing(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        return recipeService.recipeEditing(id, recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта по id")
    @Parameters(value ={
            @Parameter(name = "Название рецепта", example = "Плов")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удален",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))
                    })
    })
    public Recipe removeRecipe(@PathVariable("id") long id) {
        return recipeService.remove(id);
    }

    @GetMapping
    @Operation(summary = "Получение полного списка рецептов")
    @Parameters(value ={
            @Parameter(name = "Название рецепта", example = "Плов")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Полный список рецептов получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))
                    })
    })
    public List<Recipe> getAll() {
        return this.recipeService.getAll();
    }
}

