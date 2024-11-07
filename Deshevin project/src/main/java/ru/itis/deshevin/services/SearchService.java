package ru.itis.deshevin.services;

import ru.itis.deshevin.dto.DrugDto;
import ru.itis.deshevin.models.UserEntity;
import ru.itis.deshevin.security.details.UserEntityDetails;

import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.UUID;

public interface SearchService {

    List<DrugDto> getDrugsWithSameAnalogueClassAs(UUID drugId);

}
