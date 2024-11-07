package ru.itis.deshevin.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.itis.deshevin.dto.AddDrugDto;
import ru.itis.deshevin.dto.DrugDto;
import ru.itis.deshevin.mappers.DrugMapper;
import ru.itis.deshevin.models.AnalogueClassEntity;
import ru.itis.deshevin.models.DrugEntity;
import ru.itis.deshevin.models.FileInfoEntity;
import ru.itis.deshevin.repositories.DrugRepository;
import ru.itis.deshevin.services.CategoryService;
import ru.itis.deshevin.services.DrugService;
import ru.itis.deshevin.services.FilesService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements DrugService {

    private final DrugMapper drugMapper;
    private final DrugRepository drugRepository;
    private final CategoryService categoryService;
    private final FilesService filesService;

    @Override
    @Transactional
    public void saveDrug(AddDrugDto addDrugDto) {
        log.info("Start saving drug = " + addDrugDto);
        FileInfoEntity newFile = null;
        if (addDrugDto.getFile() != null) {
            newFile = filesService.saveFileToStorage(addDrugDto.getFile()).get();
        }
        DrugEntity newDrug = drugMapper.toDrugEntity(addDrugDto);
        newDrug.setDrugsCategory(categoryService.getCategoriesById(addDrugDto.getCategoryIdSet()));
        newDrug.setPhoto(newFile);
        drugRepository.save(newDrug);
        log.info("Finish saving drug");
    }

    @Override
    @Transactional
    public void updateDrug(AddDrugDto addDrugDto, UUID id) {
        log.info("Start updating drug with id = " + id + " " + addDrugDto);
        DrugEntity drugEntity = drugRepository.findById(id).orElseGet(DrugEntity::new);
        if (addDrugDto.getAnalogueId() != null) {
            drugEntity.setAnalogueClass(
                    AnalogueClassEntity
                            .builder()
                            .id(addDrugDto.getAnalogueId())
                            .build()
            );
        }
        if (!addDrugDto.getCategoryIdSet().isEmpty()) {
            drugEntity.setDrugsCategory(categoryService.getCategoriesById(addDrugDto.getCategoryIdSet()));
        }
        drugEntity.setTitle(addDrugDto.getTitle());
        drugEntity.setDescription(addDrugDto.getDescription());
        drugEntity.setComposition(addDrugDto.getComposition());
        drugEntity.setManufacturer(addDrugDto.getManufacturer());
        drugEntity.setContraindications(addDrugDto.getContraindications());
        drugEntity.setSideEffects(addDrugDto.getSideEffects());
        drugEntity.setReleaseForm(addDrugDto.getReleaseForm());
        drugEntity.setEffect(addDrugDto.getEffect());
        drugEntity.setInstruction(addDrugDto.getInstruction());
        drugEntity.setStorageConditions(addDrugDto.getStorageConditions());
        drugRepository.save(drugEntity);
        log.info("Finish updating drug");
    }

    @Override
    public List<DrugDto> getAllDrugs(String prefix) {
        log.info("Drug search by prefix {}", prefix);
        List<DrugDto> drugDtoList = drugMapper.toDrudListDto(drugRepository.findAllByTitleContainingIgnoreCase(prefix))
                .stream()
                .peek(
                        drug -> drug.setDescription(drug.getDescription().substring(0, Integer.min(100, drug.getDescription().length())))
                ).collect(Collectors.toList());
        log.info(drugDtoList);
        return drugDtoList;
    }

    @Override
    public DrugDto getDrugById(UUID id) {
        return drugMapper.toDrugDto(drugRepository.findById(id).orElseGet(DrugEntity::new));
    }

    @Override
    public void deleteDrugBuId(UUID id) {
        log.info("Start deleting drug with id = " + id);
        drugRepository.findById(id).ifPresent(
                drug -> drug.getUsers().forEach(
                        user -> user.getFavorites().remove(drug)
                )
        );
        drugRepository.deleteById(id);
        log.info("Finish deleting drug");
    }
}
