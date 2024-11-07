package ru.itis.deshevin.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.itis.deshevin.dto.DrugDto;
import ru.itis.deshevin.mappers.DrugMapper;
import ru.itis.deshevin.models.DrugEntity;
import ru.itis.deshevin.models.UserEntity;
import ru.itis.deshevin.repositories.DrugRepository;
import ru.itis.deshevin.repositories.UserRepository;
import ru.itis.deshevin.services.FavouritesService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class FavouritesServiceImpl implements FavouritesService {

    private final DrugRepository drugRepository;
    private final UserRepository userRepository;
    private final DrugMapper drugMapper;

    @Override
    public void addDrugToFavourites(UUID userId, UUID drugId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        DrugEntity drug = drugRepository.findById(drugId).orElseThrow();

        if(!user.getFavorites().contains(drug)) user.getFavorites().add(drug);

        userRepository.save(user);
        log.info("Successfuly add drug: " + drug + "to account: " + user);
    }

    @Override
    public List<DrugDto> getFavouriteDrugs(UUID userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        log.info("Successfuly return Set of drugs for account: " + user);
        log.info("all fav " + user.getFavorites());
        return drugMapper.toDrudListDto(new ArrayList<>(user.getFavorites()));
    }

    @Override
    public void deleteDrugFromFavourites(UUID userId, UUID drugId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        DrugEntity drug = drugRepository.findById(drugId).orElseThrow();

        user.getFavorites().remove(drug);

        userRepository.save(user);
        log.info("Successfuly delete drug: " + drug + "from account: " + user);
    }
}
