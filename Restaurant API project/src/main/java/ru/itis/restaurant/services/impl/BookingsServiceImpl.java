package ru.itis.restaurant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.restaurant.dto.BookingDto;
import ru.itis.restaurant.dto.forms.BookingForm;
import ru.itis.restaurant.exceptions.BookingNotFoundException;
import ru.itis.restaurant.exceptions.InvalidBookingDataException;
import ru.itis.restaurant.exceptions.SpotNotFoundException;
import ru.itis.restaurant.models.Account;
import ru.itis.restaurant.models.Booking;
import ru.itis.restaurant.models.Spot;
import ru.itis.restaurant.repositories.BookingsRepository;
import ru.itis.restaurant.repositories.SpotsRepository;
import ru.itis.restaurant.services.BookingsService;
import ru.itis.restaurant.services.DateService;
import ru.itis.restaurant.services.SecurityService;

import java.text.ParseException;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class BookingsServiceImpl implements BookingsService {
    private final BookingsRepository bookingsRepository;
    private final SpotsRepository spotsRepository;
    private final DateService dateService;
    private final SecurityService securityService;

    @Override
    public BookingDto bookTable(BookingForm bookingForm) {
        Spot spot = spotsRepository.findByTableNumber(bookingForm.getTableNumber()).orElseThrow((Supplier<RuntimeException>) ()
                -> new SpotNotFoundException("Table with this number not found")
        );

        Account account = securityService.getAuthorizedAccount();

        Booking newBooking = Booking.builder()
                .bookedFrom(bookingForm.getBookedFrom())
                .bookedTo(bookingForm.getBookedTo())
                .spot(spot)
                .state(Booking.State.ACTIVE)
                .account(account)
                .build();

        List<Booking> bookings = bookingsRepository.findAllBySpotAndStateEquals(spot, Booking.State.ACTIVE);
        if (bookings.isEmpty()) {
            bookingsRepository.save(newBooking);
        } else {
            boolean flag = true;
            for (Booking booking : bookings) {
                try {
                    flag = dateService.checkDate(booking.getBookedFrom(), booking.getBookedTo(), newBooking.getBookedFrom(), newBooking.getBookedTo());
                } catch (ParseException e) {
                    throw new InvalidBookingDataException();
                }
            }

            if (flag) {
                bookingsRepository.save(newBooking);
            } else {
                throw new InvalidBookingDataException();
            }
        }

        return BookingDto.from(newBooking);
    }

    @Override
    public BookingDto cancelBooking(Long bookingId) {
        Booking booking = bookingsRepository.findById(bookingId).orElseThrow((Supplier<RuntimeException>) ()
                -> new BookingNotFoundException("Booking not found")
        );

        booking.setState(Booking.State.CANCELED);

        return BookingDto.from(bookingsRepository.save(booking));
    }

    @Override
    public List<BookingDto> getAllAccountBookings() {
        Account account = securityService.getAuthorizedAccount();
        List<Booking> bookings = bookingsRepository.findAllByAccountAndStateEquals(account, Booking.State.ACTIVE);

        return BookingDto.from(bookings);
    }
}
