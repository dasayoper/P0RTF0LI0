package ru.itis.restaurant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.restaurant.dto.AccountDto;
import ru.itis.restaurant.dto.forms.SignUpForm;
import ru.itis.restaurant.exceptions.AccountNotFoundException;
import ru.itis.restaurant.models.Account;
import ru.itis.restaurant.repositories.AccountsRepository;
import ru.itis.restaurant.services.AccountsService;
import ru.itis.restaurant.services.SecurityService;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;
    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<AccountDto> getAllAccounts() {
        return AccountDto.from(accountsRepository.findAll());
    }

    @Override
    public AccountDto updateAccount(SignUpForm newData) {
        Account account = securityService.getAuthorizedAccount();
        account.setFirstName(newData.getFirstName());
        account.setLastName(newData.getLastName());
        account.setEmail(newData.getEmail());
        account.setPhoneNumber(newData.getPhoneNumber());
        account.setPassword(passwordEncoder.encode(newData.getPassword()));

        return AccountDto.from(accountsRepository.save(account));
    }

    @Override
    public AccountDto banAccount(Long accountId) {
        Account account = accountsRepository.findById(accountId).orElseThrow((Supplier<RuntimeException>) ()
                -> new AccountNotFoundException("Account with such id did not exists")
        );
        account.setState(Account.State.BANNED);

        return AccountDto.from(accountsRepository.save(account));
    }
}
