package com.skypro.recipes.controller;
import com.skypro.recipes.model.Ingredient;
import com.skypro.recipes.model.Recipe;
import com.skypro.recipes.service.IngredientService;
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
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "CRUD-операции для работы с ингредиентами")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(summary = "Добавление ингредиента")
    @Parameters(value ={
            @Parameter(name = "Наименование ингредиента", example = "Рис")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))
                    })
    })
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.add(ingredient);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение ингредиента по id")
    @Parameters(value ={
            @Parameter(name = "Наименование ингредиента", example = "Рис")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))
                    })
    })
    public Ingredient getIngredient(@PathVariable("id") long id) {
        return ingredientService.get(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование ингредиента")
    @Parameters(value ={
            @Parameter(name = "Наименование ингредиента", example = "Рис")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент отредактирован",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))
                    })
    })
    public Ingredient ingredientEditing(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        return ingredientService.inredientEditing(id, ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента по id")
    @Parameters(value ={
            @Parameter(name = "Наименование ингредиента", example = "Рис")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент удален",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))
                    })
    })
    public Ingredient removeRecipe(@PathVariable("id") long id) {
        return ingredientService.remove(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка всех ингредиентов")
    @Parameters(value ={
            @Parameter(name = "Наименование ингредиента", example = "Рис")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))
                    })
    })
    public List<Ingredient> getAll() {
        return this.ingredientService.getAll();
    }
}
