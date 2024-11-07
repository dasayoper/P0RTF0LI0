package ru.itis.restaurant.dto.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignUpForm {
    @NotBlank
    @Schema(description = "Your first name", example = "Ivan")
    private String firstName;

    @NotBlank
    @Schema(description = "Your last name", example = "Petrov")
    private String lastName;

    @Pattern(message = "email should looks like email", regexp = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$")
    @Schema(description = "Your existing email address", example = "IvanPetrov01@gmail.com")
    private String email;

    @NotBlank
    @Size(min = 6, message = "minimal size of password {min} symbols")
    @Schema(description = "Your not qwerty007 password", example = "yakrutoiprogerzakazhukayapizzu")
    private String password;

    @Pattern(message = "phone number is incorrect", regexp = "\\+79[0-9]{9}|8[0-9]{9}")
    @Schema(description = "Your existing phone number to contact the courier", example = "+79123456789")
    private String phoneNumber;

}
