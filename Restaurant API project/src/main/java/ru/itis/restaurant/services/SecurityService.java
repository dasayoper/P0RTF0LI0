package ru.itis.restaurant.services;

import ru.itis.restaurant.models.Account;

public interface SecurityService {
    Account getAuthorizedAccount();
}
