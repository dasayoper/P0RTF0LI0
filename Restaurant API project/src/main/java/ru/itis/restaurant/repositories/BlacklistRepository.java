package ru.itis.restaurant.repositories;

public interface BlacklistRepository {
    void save(String token);
    boolean exists(String token);
}
