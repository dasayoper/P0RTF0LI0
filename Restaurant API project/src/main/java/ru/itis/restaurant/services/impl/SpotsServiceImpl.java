package ru.itis.restaurant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.restaurant.dto.SpotDto;
import ru.itis.restaurant.dto.forms.SpotForm;
import ru.itis.restaurant.exceptions.SpotNotFoundException;
import ru.itis.restaurant.models.Spot;
import ru.itis.restaurant.repositories.SpotsRepository;
import ru.itis.restaurant.services.SpotsService;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class SpotsServiceImpl implements SpotsService {
    private final SpotsRepository spotsRepository;

    @Override
    public SpotDto addSpot(SpotForm spotForm) {
        Spot newSpot = Spot.builder()
                .tableNumber(spotForm.getTableNumber())
                .seats(spotForm.getSeats())
                .build();

        return SpotDto.from(spotsRepository.save(newSpot));
    }

    @Override
    public SpotDto updateSpot(Long spotId, SpotForm newData) {
        Spot spot = spotsRepository.findById(spotId).orElseThrow((Supplier<RuntimeException>) ()
                -> new SpotNotFoundException("Spot not found")
        );
        spot.setTableNumber(newData.getTableNumber());
        spot.setSeats(newData.getSeats());

        return SpotDto.from(spotsRepository.save(spot));
    }

    @Override
    public List<SpotDto> getAllSpots() {
        List<Spot> spots = spotsRepository.findAll();
        return SpotDto.from(spots);
    }
}
