package ru.itis.restaurant.dto.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignInForm {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
