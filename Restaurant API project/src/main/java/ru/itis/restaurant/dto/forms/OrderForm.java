package ru.itis.restaurant.dto.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderForm {
    @NotBlank
    @Schema(description = "Address where you are waiting for the order", example = "DU, house 18, room 209")
    private String destinationAddress;

    @NotNull
    @Schema(description = "List of id of menu items you want to order", example = "1, 2, 3")
    private List<Long> itemIds;
}
