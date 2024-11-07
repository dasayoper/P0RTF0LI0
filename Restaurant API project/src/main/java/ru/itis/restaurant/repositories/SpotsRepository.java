package ru.itis.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.restaurant.models.Spot;

import java.util.Optional;

public interface SpotsRepository extends JpaRepository<Spot, Long> {
    Optional<Spot> findByTableNumber(Integer tableNumber);
}
