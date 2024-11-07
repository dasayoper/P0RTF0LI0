package ru.itis.eyejust.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.eyejust.models.MedicalReport;

import java.util.List;
import java.util.UUID;

public interface MedicalReportRepository extends JpaRepository<MedicalReport, UUID> {
    List<MedicalReport> findByUserIdOrderByCreationDateDesc(UUID userId);
}
