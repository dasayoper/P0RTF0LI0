package ru.itis.deshevin.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.itis.deshevin.dto.AnalogueClassDto;
import ru.itis.deshevin.mappers.AnalogueClassMapper;
import ru.itis.deshevin.repositories.AnalogueClassRepository;
import ru.itis.deshevin.services.AnalogueClassService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class AnalogueClassServiceImpl implements AnalogueClassService {

    private final AnalogueClassMapper analogueClassMapper;
    private final AnalogueClassRepository analogueClassRepository;

    @Override
    public List<AnalogueClassDto> getAllAnalogueClass() {
        log.info("Get all Analogue Classes");
        return analogueClassMapper.toAnalogueClassDtoList(analogueClassRepository.findAll());
    }

    @Override
    @Transactional
    public void saveAnalogueClass(AnalogueClassDto analogueClassDto) {
        log.info("Saving Analogue class " + analogueClassDto);
        analogueClassRepository.save(analogueClassMapper.toAnalogueClassEntity(analogueClassDto));
    }

    @Override
    @Transactional
    public void deleteAnalogueClass(UUID id) {
        log.info("Delete Analogue class with id = " + id);
        analogueClassRepository.findById(id).ifPresent(
                analogue -> analogue.getDrugs().forEach(drug -> {
                    log.info("Delete drug related with analogue class = " + drug);
                    drug.setAnalogueClass(null);
                })
        );
        analogueClassRepository.deleteById(id);
    }
}
