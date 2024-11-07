package ru.itis.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.restaurant.models.Booking;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Long id;

    private String bookedFrom;

    private String bookedTo;

    private Long spotId;

    private Long accountId;

    private String state;

    public static BookingDto from(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .bookedFrom(booking.getBookedFrom())
                .bookedTo(booking.getBookedTo())
                .spotId(booking.getSpot().getId())
                .accountId(booking.getAccount().getId())
                .state(booking.getState().toString())
                .build();
    }

    public static List<BookingDto> from(List<Booking> bookings) {
        return bookings.stream().map(BookingDto::from).collect(Collectors.toList());
    }
}
