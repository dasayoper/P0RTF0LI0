package ru.itis.eyejust.services;

import ru.itis.eyejust.dto.UserInfoFormDto;
import ru.itis.eyejust.models.UserEntity;

import java.util.UUID;

public interface UserEntityService {
    UserEntity updateUserInfo(UserInfoFormDto userInfoFormDto, UUID userId);
}
