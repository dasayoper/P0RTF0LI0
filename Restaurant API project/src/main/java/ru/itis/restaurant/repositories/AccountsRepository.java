package ru.itis.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.restaurant.models.Account;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByConfirmCode(String confirmCode);

    Optional<Account> findByEmail(String email);
}
