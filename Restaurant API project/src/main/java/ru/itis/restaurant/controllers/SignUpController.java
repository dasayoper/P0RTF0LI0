package ru.itis.restaurant.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.restaurant.SignUpApi;
import ru.itis.restaurant.dto.AccountDto;
import ru.itis.restaurant.dto.forms.SignUpForm;
import ru.itis.restaurant.services.SignUpService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class SignUpController implements SignUpApi {
    private final SignUpService signUpService;

    @Override
    public ResponseEntity<AccountDto> signUp(@RequestBody @Valid SignUpForm signUpForm) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(signUpService.signUp(signUpForm));
    }

    @Override
    public ResponseEntity<Void> checkConfirm(@PathVariable("confirm-code") String confirmCode) {
        signUpService.checkConfirm(confirmCode);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
