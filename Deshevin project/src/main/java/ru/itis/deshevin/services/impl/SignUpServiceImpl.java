package ru.itis.deshevin.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.deshevin.dto.SignUpDto;
import ru.itis.deshevin.dto.UserDto;
import ru.itis.deshevin.enums.Role;
import ru.itis.deshevin.enums.Status;
import ru.itis.deshevin.mappers.UserEntityMapper;
import ru.itis.deshevin.models.UserEntity;
import ru.itis.deshevin.repositories.UserRepository;
import ru.itis.deshevin.services.SignUpService;

import java.util.HashSet;
import java.util.Set;


@Log4j2
@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto signUp(SignUpDto signUpDto) {
        log.info("Start registering account: " + signUpDto);
        if (userRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            log.info("Error registering account: " + signUpDto);
            return null;
        }

        UserEntity newUser = UserEntity.builder()
                .username(signUpDto.getUsername())
                .firstName(signUpDto.getFirstName())
                .lastName(signUpDto.getLastName())
                .city(signUpDto.getCity())
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Role.COMMON_USER)
                .status(Status.NORMAL)
                .favorites(new HashSet<>())
                .build();

        userRepository.save(newUser);
        log.info("Successful save  of account: " + newUser);

        return userEntityMapper.toUserDto(newUser);
    }
}
