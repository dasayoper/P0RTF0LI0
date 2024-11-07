package ru.itis.eyejust.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import ru.itis.eyejust.dto.MedicalReportDto;
import ru.itis.eyejust.dto.ReportFormDto;
import ru.itis.eyejust.dto.UploadReportFormDto;
import ru.itis.eyejust.exceptions.ReportNotFoundException;
import ru.itis.eyejust.exceptions.UserNotFoundException;
import ru.itis.eyejust.mappers.MedicalReportMapper;
import ru.itis.eyejust.models.FileInfo;
import ru.itis.eyejust.models.MedicalReport;
import ru.itis.eyejust.models.UserEntity;
import ru.itis.eyejust.models.enums.Status;
import ru.itis.eyejust.repositories.MedicalReportRepository;
import ru.itis.eyejust.repositories.UserRepository;
import ru.itis.eyejust.repositories.mongo.FileInfoRepository;
import ru.itis.eyejust.services.MedicalReportService;
import ru.itis.eyejust.utils.PdfReportService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MedicalReportServiceImpl implements MedicalReportService {

    private final MedicalReportRepository medicalReportRepository;
    private final UserRepository userRepository;
    private final FileInfoRepository fileInfoRepository;
    private final PdfReportService pdfReportService;
    private final MedicalReportMapper medicalReportMapper;

    @Override
    @Transactional
    public void saveReport(ReportFormDto reportFormDto, UUID userId) {
        log.info("start saving new report");
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));;

        try {
            byte[] pdfContent = pdfReportService.createPdf(reportFormDto, user);

            String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

            FileInfo newReportPDF = FileInfo.builder()
                    .id(UUID.randomUUID().toString())
                    .fileSize(String.valueOf(pdfContent.length))
                    .fileType("application/pdf")
                    .data(new Binary(BsonBinarySubType.BINARY, pdfContent))
                    .userId(userId.toString())
                    .build();

            newReportPDF.setFileName(user.getLastName() + "_" + date + ".pdf");
            fileInfoRepository.insert(newReportPDF);
            log.info("successfully save report PDF file with id " + newReportPDF.getId() + " in MongoDB");

            MedicalReport newReport = MedicalReport.builder()
                    .creationDate((LocalDate.parse(date,
                            DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss"))).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                    .status(Status.NORMAL)
                    .leftEyeImageDBId(reportFormDto.getLeftEyeImageId())
                    .rightEyeImageDBId(reportFormDto.getRightEyeImageId())
                    .leftEyeDiagnosticValue(reportFormDto.getLeftEyeDiagnosticValue())
                    .rightEyeDiagnosticValue(reportFormDto.getRightEyeDiagnosticValue())
                    .leftEyeCDR(reportFormDto.getLeftEyeCDR())
                    .rightEyeCDR(reportFormDto.getRightEyeCDR())
                    .user(user)
                    .reportFileDBId(newReportPDF.getId())
                    .build();

            medicalReportRepository.save(newReport);
            log.info("successfully save report with id " + newReport.getId());
        } catch (IOException e) {
            log.error("Error creating PDF report", e);
            throw new RuntimeException("Error creating PDF report", e);
        } catch (Exception e) {
            log.error("Unexpected error", e);
            throw new RuntimeException("Unexpected error", e);
        }
    }

    @Override
    public List<MedicalReportDto> getAllUserReports(UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        List<MedicalReport> reportList = medicalReportRepository.findByUserIdOrderByCreationDateDesc(user.getId());

        return medicalReportMapper.toMedicalReportDtoList(reportList);
    }

    @Override
    public void deleteReportById(UUID reportId) {
        log.info("start deleting report with id " + reportId);
        MedicalReport medicalReport = medicalReportRepository.findById(reportId)
                .orElseThrow(() -> new ReportNotFoundException("Report not found with ID: " + reportId));
        medicalReport.setStatus(Status.DELETED);

        medicalReportRepository.save(medicalReport);
        log.info("successfully delete report with id " + medicalReport.getId());
    }

    @Override
    @Transactional
    public void uploadReport(UploadReportFormDto uploadReportFormDto, UUID userId) {
        log.info("start saving report uploaded  by user " + userId);
        try {
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

            FileInfo reportPDF = FileInfo.builder()
                    .id(UUID.randomUUID().toString())
                    .fileName(uploadReportFormDto.getReportFile().getOriginalFilename())
                    .fileSize(String.valueOf(uploadReportFormDto.getReportFile().getSize()))
                    .fileType("application/pdf")
                    .data(new Binary(BsonBinarySubType.BINARY, uploadReportFormDto.getReportFile().getBytes()))
                    .userId(userId.toString())
                    .build();

            fileInfoRepository.insert(reportPDF);
            log.info("successfully save uploaded report PDF file with id " + reportPDF.getId() + " in MongoDB");


            MedicalReport newReport = MedicalReport.builder()
                    .creationDate((LocalDate.parse(uploadReportFormDto.getCreationDate(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"))).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                    .status(Status.NORMAL)
                    .leftEyeImageDBId(uploadReportFormDto.getLeftEyeImageDBId())
                    .rightEyeImageDBId(uploadReportFormDto.getRightEyeImageDBId())
                    .leftEyeDiagnosticValue(uploadReportFormDto.getLeftEyeDiagnosticValue())
                    .rightEyeDiagnosticValue(uploadReportFormDto.getRightEyeDiagnosticValue())
                    .leftEyeCDR(uploadReportFormDto.getLeftEyeCDR())
                    .rightEyeCDR(uploadReportFormDto.getRightEyeCDR())
                    .user(user)
                    .reportFileDBId(reportPDF.getId())
                    .build();

            medicalReportRepository.save(newReport);
            log.info("successfully save uploaded report with id " + newReport.getId());
        } catch (IOException e) {
            log.error("Error creating PDF report", e);
            throw new RuntimeException("Error creating PDF report", e);
        } catch (Exception e) {
            log.error("Unexpected error", e);
            throw new RuntimeException("Unexpected error", e);
        }
    }
}
