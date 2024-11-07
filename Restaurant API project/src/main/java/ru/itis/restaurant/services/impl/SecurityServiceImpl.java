package ru.itis.restaurant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.restaurant.models.Account;
import ru.itis.restaurant.security.details.AccountUserDetails;
import ru.itis.restaurant.services.SecurityService;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    @Override
    public Account getAuthorizedAccount() {
        return ((AccountUserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials()).getAccount();
    }
}
