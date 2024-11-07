package ru.itis.deshevin.services;

import ru.itis.deshevin.models.UserEntity;
import ru.itis.deshevin.security.details.UserEntityDetails;

import java.util.Optional;

public interface UserService {

    Optional<UserEntity> getUserByAuth(UserEntityDetails userEntityDetails);
}
