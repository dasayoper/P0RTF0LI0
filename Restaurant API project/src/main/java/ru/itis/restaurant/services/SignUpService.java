package ru.itis.restaurant.services;

import ru.itis.restaurant.dto.AccountDto;
import ru.itis.restaurant.dto.forms.SignUpForm;

public interface SignUpService {
    AccountDto signUp(SignUpForm signUpForm);

    void checkConfirm(String confirmCode);
}
