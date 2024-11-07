package ru.itis.deshevin.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.deshevin.dto.DrugDto;
import ru.itis.deshevin.mappers.DrugMapper;
import ru.itis.deshevin.models.DrugEntity;
import ru.itis.deshevin.repositories.DrugRepository;
import ru.itis.deshevin.services.SearchService;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final DrugRepository drugRepository;

    private final DrugMapper drugMapper;

    @Override
    public List<DrugDto> getDrugsWithSameAnalogueClassAs(UUID drugId) {
        log.info("Search for drugs in same analogue class with {}", drugId);
        Optional<DrugEntity> optionalDrug = drugRepository.findById(drugId);
        if(optionalDrug.isEmpty()) {
            log.error("Drug not found! id = {}", drugId);
            return new ArrayList<>();
        }
        DrugEntity drug = optionalDrug.get();
        return drugMapper.toDrudListDto(drugRepository.findAllByAnalogueClass(drug.getAnalogueClass()));
    }
}
