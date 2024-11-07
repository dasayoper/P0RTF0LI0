package ru.itis.deshevin.services;

import ru.itis.deshevin.dto.DrugDto;

import java.util.List;
import java.util.Set;
import java.util.Set;
import java.util.UUID;

public interface FavouritesService {

    void addDrugToFavourites(UUID userId, UUID drugId);

    List<DrugDto> getFavouriteDrugs(UUID userId);

    void deleteDrugFromFavourites(UUID userId, UUID drugId);
}
