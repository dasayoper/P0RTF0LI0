package ru.itis.restaurant.dto.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SpotForm {
    @NotNull
    @Schema(description = "Number of table you want to add", example = "1")
    private Integer tableNumber;

    @NotNull
    @Schema(description = "Maximum number of people that can fit at the table", example = "6")
    private Integer seats;

}
