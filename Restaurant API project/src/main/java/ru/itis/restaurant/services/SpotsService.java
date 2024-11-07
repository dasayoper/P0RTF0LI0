package ru.itis.restaurant.services;

import ru.itis.restaurant.dto.SpotDto;
import ru.itis.restaurant.dto.forms.SpotForm;

import java.util.List;

public interface SpotsService {
    SpotDto addSpot(SpotForm spotForm);
    SpotDto updateSpot(Long spotId, SpotForm newData);
    List<SpotDto> getAllSpots();
}
