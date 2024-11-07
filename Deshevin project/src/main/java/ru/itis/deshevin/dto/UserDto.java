package ru.itis.deshevin.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String city;
    private String email;
}
