package ru.itis.restaurant.dto.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.itis.restaurant.validation.annotations.ValidData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ValidData(fields = {"bookedFrom", "bookedTo"}, message = "{dates} are invalid")
public class BookingForm {
    @NotBlank
    @Schema(description = "Time when you want to arrive", example = "2022-01-01 13:00")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}", message = "date is in the wrong format")
    private String bookedFrom;

    @NotBlank
    @Schema(description = "Time when you want to leave", example = "2022-01-01 18:00")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}", message = "date is in the wrong format")
    private String bookedTo;

    @NotNull
    @Schema(description = "Number of table you want to book", example = "1")
    private Integer tableNumber;
}
