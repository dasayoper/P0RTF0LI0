package ru.itis.eyejust.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiagnosticResultDto {
    private String leftEyeDiagnosticValue;
    private String rightEyeDiagnosticValue;
    private String leftEyeCroppedImageId;
    private String rightEyeCroppedImageId;
    private String leftEyeMaskImageId;
    private String rightEyeMaskImageId;
    private String leftEyeCDR;
    private String rightEyeCDR;
}
