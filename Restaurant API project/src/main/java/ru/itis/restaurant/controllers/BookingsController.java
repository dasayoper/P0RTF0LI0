package ru.itis.restaurant.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.restaurant.BookingsApi;
import ru.itis.restaurant.dto.BookingDto;
import ru.itis.restaurant.dto.forms.BookingForm;
import ru.itis.restaurant.services.BookingsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingsController implements BookingsApi {
    private final BookingsService bookingsService;

    @Override
    public ResponseEntity<BookingDto> bookTable(@RequestBody @Valid BookingForm bookingForm) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookingsService.bookTable(bookingForm));
    }

    @Override
    public ResponseEntity<BookingDto> cancelBooking(@PathVariable("booking-id") Long bookingId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(bookingsService.cancelBooking(bookingId));
    }

    @Override
    public ResponseEntity<List<BookingDto>> getAllAccountBookings() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookingsService.getAllAccountBookings());
    }

}
