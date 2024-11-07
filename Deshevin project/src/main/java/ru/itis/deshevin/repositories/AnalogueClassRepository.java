package ru.itis.deshevin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.deshevin.models.AnalogueClassEntity;

import java.util.UUID;

public interface AnalogueClassRepository extends JpaRepository<AnalogueClassEntity, UUID> {
}
