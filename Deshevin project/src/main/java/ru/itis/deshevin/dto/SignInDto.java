package ru.itis.deshevin.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInDto {
    private String email;
    private String password;
}
