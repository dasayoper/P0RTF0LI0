package ru.itis.deshevin.services;

import ru.itis.deshevin.dto.SignUpDto;
import ru.itis.deshevin.dto.UserDto;

public interface SignUpService {
    UserDto signUp(SignUpDto signUpDto);
}
