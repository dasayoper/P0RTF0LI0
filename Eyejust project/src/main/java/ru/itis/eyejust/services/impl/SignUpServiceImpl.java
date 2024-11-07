package ru.itis.eyejust.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.eyejust.dto.SignUpDto;
import ru.itis.eyejust.dto.UserDto;
import ru.itis.eyejust.exceptions.UserAlreadyExistsException;
import ru.itis.eyejust.mappers.UserEntityMapper;
import ru.itis.eyejust.models.UserEntity;
import ru.itis.eyejust.models.enums.Role;
import ru.itis.eyejust.models.enums.State;
import ru.itis.eyejust.models.enums.Status;
import ru.itis.eyejust.repositories.UserRepository;
import ru.itis.eyejust.services.SignUpService;

import java.util.HashSet;


@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto signUp(SignUpDto signUpDto) {
        log.info("start registering new user");
        if (userRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + signUpDto.getEmail());
        }

        UserEntity newUser = UserEntity.builder()
                .firstName(signUpDto.getFirstName())
                .lastName(signUpDto.getLastName())
                .patronymic(signUpDto.getPatronymic())
                .birthdate(signUpDto.getBirthdate())
                .gender(signUpDto.getGender())
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Role.COMMON_USER)
                .status(Status.NORMAL)
                .state(State.NOT_COMPLETED)
                .medicalReports(new HashSet<>())
                .address("не указано")
                .pastIllnesses("не указано")
                .chronicDiseases("не указано")
                .surgeries("не указано")
                .drugIntolerance("не указано")
                .badHabits("не указано")
                .build();

        userRepository.save(newUser);
        log.info("successfully register user with email: " + newUser.getEmail() + " and id: " + newUser.getId());

        return userEntityMapper.toUserDto(newUser);
    }
}
