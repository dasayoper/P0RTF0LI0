package ru.itis.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.restaurant.models.Account;
import ru.itis.restaurant.models.Booking;
import ru.itis.restaurant.models.Spot;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllBySpotAndStateEquals(Spot spot, Enum state);
    List<Booking> findAllByAccountAndStateEquals(Account account, Enum state);
}
