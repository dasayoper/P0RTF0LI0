package ru.itis.restaurant.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.restaurant.SpotsApi;
import ru.itis.restaurant.dto.SpotDto;
import ru.itis.restaurant.dto.forms.SpotForm;
import ru.itis.restaurant.services.SpotsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpotsController implements SpotsApi {
    private final SpotsService spotsService;

    @Override
    public ResponseEntity<SpotDto> addSpot(@RequestBody @Valid SpotForm spotForm) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(spotsService.addSpot(spotForm));
    }

    @Override
    public ResponseEntity<SpotDto> updateSpot(@PathVariable("spot-id") Long spotId,
                                              @RequestBody @Valid SpotForm newData) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(spotsService.updateSpot(spotId, newData));
    }

    @Override
    public ResponseEntity<List<SpotDto>> getAllSpots() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(spotsService.getAllSpots());
    }

}
