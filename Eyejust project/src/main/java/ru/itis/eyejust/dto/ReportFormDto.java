package ru.itis.eyejust.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportFormDto {
    private String therapist;
    private String medicalInstitution;
    private String complaints;
    private String diseaseAnamnesis;
    private String objectiveStatus;
    private String localStatus;
    private String diagnosis;
    private String conclusion;
    private String recommendations;
    private String leftEyeImageId;
    private String rightEyeImageId;
    private String leftEyeDiagnosticValue;
    private String rightEyeDiagnosticValue;
    private String leftEyeCroppedImageId;
    private String rightEyeCroppedImageId;
    private String leftEyeMaskImageId;
    private String rightEyeMaskImageId;
    private String leftEyeCDR;
    private String rightEyeCDR;
}