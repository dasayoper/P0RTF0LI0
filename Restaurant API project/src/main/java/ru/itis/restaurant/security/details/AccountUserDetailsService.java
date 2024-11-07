package ru.itis.restaurant.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.restaurant.exceptions.AccountNotFoundException;
import ru.itis.restaurant.repositories.AccountsRepository;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AccountUserDetailsService implements UserDetailsService {
    private final AccountsRepository accountsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AccountUserDetails(accountsRepository.findByEmail(email)
                .orElseThrow((Supplier<RuntimeException>) ()
                        -> new AccountNotFoundException("Account with such id did not exists")
                )
        );
    }
}
