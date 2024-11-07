package ru.itis.deshevin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.deshevin.models.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
