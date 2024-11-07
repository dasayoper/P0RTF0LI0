package ru.itis.restaurant.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.restaurant.AccountsApi;
import ru.itis.restaurant.dto.AccountDto;
import ru.itis.restaurant.dto.forms.SignUpForm;
import ru.itis.restaurant.services.AccountsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountsController implements AccountsApi {
    private final AccountsService accountsService;

    @Override
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsService.getAllAccounts());
    }

    @Override
    public ResponseEntity<AccountDto> updateAccount(@RequestBody @Valid SignUpForm newData) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(accountsService.updateAccount(newData));
    }

    @Override
    public ResponseEntity<AccountDto> banAccount(@PathVariable("account-id") Long accountId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(accountsService.banAccount(accountId));
    }
}
