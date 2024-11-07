package ru.itis.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.restaurant.models.Order;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}
