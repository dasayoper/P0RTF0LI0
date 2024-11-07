package ru.itis.eyejust.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadReportFormDto {
    private UUID id;
    private String creationDate;
    private String leftEyeImageDBId;
    private String rightEyeImageDBId;
    private String leftEyeDiagnosticValue;
    private String rightEyeDiagnosticValue;
    private String leftEyeCDR;
    private String rightEyeCDR;
    private MultipartFile reportFile;
}
