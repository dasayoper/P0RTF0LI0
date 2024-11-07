package ru.itis.eyejust.services;

import ru.itis.eyejust.dto.MedicalReportDto;
import ru.itis.eyejust.dto.ReportFormDto;
import ru.itis.eyejust.dto.UploadReportFormDto;

import java.util.List;
import java.util.UUID;

public interface MedicalReportService {

    void saveReport(ReportFormDto reportFormDto, UUID userId);

    List<MedicalReportDto> getAllUserReports(UUID userId);

    void deleteReportById(UUID reportId);

    void uploadReport(UploadReportFormDto uploadReportFormDto, UUID userId);
}
