package ru.itis.eyejust.services;

import ru.itis.eyejust.dto.SignUpDto;
import ru.itis.eyejust.dto.UserDto;

public interface SignUpService {
    UserDto signUp(SignUpDto signUpDto);
}
