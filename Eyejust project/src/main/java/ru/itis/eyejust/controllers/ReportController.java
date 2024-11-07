package ru.itis.eyejust.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.eyejust.dto.ReportFormDto;
import ru.itis.eyejust.dto.UploadReportFormDto;
import ru.itis.eyejust.security.details.UserEntityDetails;
import ru.itis.eyejust.services.MedicalReportService;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private final MedicalReportService medicalReportService;

    @GetMapping
    public String getReportForm(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                                @RequestParam String leftEyeImageId,
                                @RequestParam String rightEyeImageId,
                                @RequestParam String leftEyeDiagnosticValue,
                                @RequestParam String rightEyeDiagnosticValue,
                                @RequestParam String leftEyeCroppedImageId,
                                @RequestParam String rightEyeCroppedImageId,
                                @RequestParam String leftEyeMaskImageId,
                                @RequestParam String rightEyeMaskImageId,
                                @RequestParam String leftEyeCDR,
                                @RequestParam String rightEyeCDR,
                                Model model) {
        model.addAttribute("leftEyeImageId", leftEyeImageId);
        model.addAttribute("rightEyeImageId", rightEyeImageId);
        model.addAttribute("leftEyeDiagnosticValue", leftEyeDiagnosticValue);
        model.addAttribute("rightEyeDiagnosticValue", rightEyeDiagnosticValue);
        model.addAttribute("leftEyeCroppedImageId", leftEyeCroppedImageId);
        model.addAttribute("rightEyeCroppedImageId", rightEyeCroppedImageId);
        model.addAttribute("leftEyeMaskImageId", leftEyeMaskImageId);
        model.addAttribute("rightEyeMaskImageId", rightEyeMaskImageId);
        model.addAttribute("leftEyeCDR", leftEyeCDR);
        model.addAttribute("rightEyeCDR", rightEyeCDR);

        model.addAttribute("user", userEntityDetails.getUserEntity());
        return "report-form";
    }

    @PostMapping
    public String saveReport(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                             @ModelAttribute ReportFormDto reportFormDto) {
        medicalReportService.saveReport(reportFormDto, userEntityDetails.getUserEntity().getId());
        return "redirect:/reports/my-reports";
    }

    @GetMapping("/my-reports")
    public String getAllUserReports(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                                    Model model) {
        model.addAttribute("reports", medicalReportService.getAllUserReports(userEntityDetails.getUserEntity().getId()));
        model.addAttribute("user", userEntityDetails.getUserEntity());
        return "user-report-table";
    }

    @PostMapping("/{id}")
    public String deleteReport(@PathVariable UUID id) {
        medicalReportService.deleteReportById(id);
        return "redirect:/reports/my-reports";
    }

    @GetMapping("/upload")
    public String getUploadReportFormPage(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                                          Model model) {
        model.addAttribute("user", userEntityDetails.getUserEntity());
        return "upload-report-form";
    }

    @PostMapping("/upload")
    public String uploadReport(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                               @ModelAttribute UploadReportFormDto uploadReportFormDto,
                               Model model) {
        medicalReportService.uploadReport(uploadReportFormDto, userEntityDetails.getUserEntity().getId());
        model.addAttribute("user", userEntityDetails.getUserEntity());
        return "redirect:/reports/my-reports";
    }
}
