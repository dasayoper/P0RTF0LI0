package ru.itis.restaurant.dto.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.itis.restaurant.validation.annotations.ValidCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MenuItemForm {
    @NotBlank
    @Schema(description = "Title of the menu item", example = "pizza 3 cheese")
    private String title;

    @NotBlank
    @Schema(description = "Description of the menu item", example = "chedder, mozarella, parmezan and sauce")
    private String description;

    @NotNull
    @Schema(description = "menu item weight in grams", example = "490")
    private Integer weight;

    @NotNull
    @Schema(description = "menu item price", example = "320")
    private Integer price;

    @ValidCategory(message = "category is invalid")
    @Schema(description = "One of the categories: DRINK, SNACK, DISH, DESSERT", example = "DISH")
    private String category;
}
