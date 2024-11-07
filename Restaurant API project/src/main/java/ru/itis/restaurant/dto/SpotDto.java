package ru.itis.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.restaurant.models.Spot;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotDto {
    private Long id;

    private Integer tableNumber;

    private Integer seats;

    private List<BookingDto> bookings;

    public static SpotDto from(Spot spot) {
        return SpotDto.builder()
                .id(spot.getId())
                .tableNumber(spot.getTableNumber())
                .seats(spot.getSeats())
                .bookings(spot.getBookings().stream().map(BookingDto::from).collect(Collectors.toList()))
                .build();
    }

    public static List<SpotDto> from(List<Spot> spots) {
        return spots.stream().map(SpotDto::from).collect(Collectors.toList());
    }
}
