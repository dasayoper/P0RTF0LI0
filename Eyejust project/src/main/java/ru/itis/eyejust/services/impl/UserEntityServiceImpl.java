package ru.itis.eyejust.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.eyejust.dto.UserInfoFormDto;
import ru.itis.eyejust.exceptions.UserNotFoundException;
import ru.itis.eyejust.models.UserEntity;
import ru.itis.eyejust.models.enums.State;
import ru.itis.eyejust.repositories.UserRepository;
import ru.itis.eyejust.services.UserEntityService;

import javax.transaction.Transactional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserEntityServiceImpl implements UserEntityService {

    private final UserRepository userRepository;

    @Override
    public UserEntity updateUserInfo(UserInfoFormDto userInfoFormDto, UUID userId) {
        log.info("start updating personal information about user " + userId);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        user.setFirstName(userInfoFormDto.getFirstName());
        user.setLastName(userInfoFormDto.getLastName());
        user.setPatronymic(userInfoFormDto.getPatronymic());
        user.setBirthdate(userInfoFormDto.getBirthdate());
        user.setGender(userInfoFormDto.getGender());
        user.setAddress(userInfoFormDto.getAddress());
        user.setPastIllnesses(userInfoFormDto.getPastIllnesses());
        user.setChronicDiseases(userInfoFormDto.getChronicDiseases());
        user.setSurgeries(userInfoFormDto.getSurgeries());
        user.setDrugIntolerance(userInfoFormDto.getDrugIntolerance());
        user.setBadHabits(userInfoFormDto.getBadHabits());
        user.setState(State.COMPLETED);

        userRepository.save(user);
        log.info("successfully update information about user " + user.getId());
        return user;
    }
}
