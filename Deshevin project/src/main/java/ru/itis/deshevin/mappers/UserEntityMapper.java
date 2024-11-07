package ru.itis.deshevin.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.itis.deshevin.dto.SignUpDto;
import ru.itis.deshevin.dto.UserDto;
import ru.itis.deshevin.models.UserEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    UserEntity toUserEntity(SignUpDto signUpDto);

    UserDto toUserDto(UserEntity userEntity);
}
