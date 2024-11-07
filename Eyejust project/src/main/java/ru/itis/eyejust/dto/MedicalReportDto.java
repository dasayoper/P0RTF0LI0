package ru.itis.eyejust.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicalReportDto {
    private UUID id;
    private String creationDate;
    private String leftEyeImageDBId;
    private String rightEyeImageDBId;
    private String leftEyeDiagnosticValue;
    private String rightEyeDiagnosticValue;
    private String leftEyeCDR;
    private String rightEyeCDR;
    private String user;
    private String reportFileDBId;
    private String status;

}
