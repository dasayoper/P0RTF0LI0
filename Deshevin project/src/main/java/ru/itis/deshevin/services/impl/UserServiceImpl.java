package ru.itis.deshevin.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.deshevin.models.UserEntity;
import ru.itis.deshevin.repositories.UserRepository;
import ru.itis.deshevin.security.details.UserEntityDetails;
import ru.itis.deshevin.services.UserService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> getUserByAuth(UserEntityDetails userEntityDetails) {
        if (userEntityDetails == null){
            return Optional.empty();
        } else {
            return userRepository.findById(userEntityDetails.getUserEntity().getId());
        }
    }

}
