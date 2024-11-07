package ru.itis.deshevin.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.itis.deshevin.dto.AnalogueClassDto;
import ru.itis.deshevin.models.AnalogueClassEntity;

import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnalogueClassMapper {
    AnalogueClassEntity toAnalogueClassEntity(AnalogueClassDto analogueClassDto);
    AnalogueClassDto toAnalogueClassDto(AnalogueClassEntity analogueClassEntity);
    List<AnalogueClassDto> toAnalogueClassDtoList(List<AnalogueClassEntity> analogueClassSet);
}
