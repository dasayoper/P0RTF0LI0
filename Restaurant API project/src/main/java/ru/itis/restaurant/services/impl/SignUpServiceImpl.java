package ru.itis.restaurant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.restaurant.dto.AccountDto;
import ru.itis.restaurant.dto.forms.SignUpForm;
import ru.itis.restaurant.exceptions.AccountNotFoundException;
import ru.itis.restaurant.models.Account;
import ru.itis.restaurant.repositories.AccountsRepository;
import ru.itis.restaurant.services.SignUpService;
import ru.itis.restaurant.util.EmailUtil;

import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final AccountsRepository accountsRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailUtil emailUtil;

    @Override
    public AccountDto signUp(SignUpForm signUpForm) {
        Account newAccount = Account.builder()
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .phoneNumber(signUpForm.getPhoneNumber())
                .confirmCode(UUID.randomUUID().toString())
                .state(Account.State.NOT_CONFIRMED)
                .role(Account.Role.USER)
                .build();

        emailUtil.sendConfirmMail(newAccount.getEmail(), "Для завершения регистрации нажмите кнопку в письме", "confirmMail.ftl", newAccount);

        return AccountDto.from(accountsRepository.save(newAccount));
    }

    @Override
    public void checkConfirm(String confirmCode) {
        Account account = accountsRepository.findAccountByConfirmCode(confirmCode).orElseThrow((Supplier<RuntimeException>) ()
                -> new AccountNotFoundException("Account not found <3")
        );

        if (account.getState().equals(Account.State.NOT_CONFIRMED)) {
            account.setState(Account.State.CONFIRMED);
            accountsRepository.save(account);
        }
    }


}
