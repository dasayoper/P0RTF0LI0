package ru.itis.eyejust.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.itis.eyejust.dto.SignUpDto;
import ru.itis.eyejust.dto.UserDto;
import ru.itis.eyejust.models.UserEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    UserEntity toUserEntity(SignUpDto signUpDto);

    UserDto toUserDto(UserEntity userEntity);
}
