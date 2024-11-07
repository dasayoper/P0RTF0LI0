package ru.itis.restaurant.services;

import ru.itis.restaurant.dto.BookingDto;
import ru.itis.restaurant.dto.forms.BookingForm;

import java.util.List;

public interface BookingsService {
    BookingDto bookTable(BookingForm bookingForm);
    BookingDto cancelBooking(Long bookingId);
    List<BookingDto> getAllAccountBookings();
}
