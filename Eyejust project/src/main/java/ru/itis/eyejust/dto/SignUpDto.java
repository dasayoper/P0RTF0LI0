package ru.itis.eyejust.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String gender;
    private String birthdate;
    private String email;
    private String password;
}

