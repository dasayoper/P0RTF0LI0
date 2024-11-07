package ru.itis.restaurant.services;

import ru.itis.restaurant.dto.AccountDto;
import ru.itis.restaurant.dto.forms.SignUpForm;

import java.util.List;

public interface AccountsService {
    List<AccountDto> getAllAccounts();
    AccountDto updateAccount(SignUpForm newData);
    AccountDto banAccount(Long accountId);
}
