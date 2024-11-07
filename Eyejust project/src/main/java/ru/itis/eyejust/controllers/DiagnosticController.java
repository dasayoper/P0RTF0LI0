package ru.itis.eyejust.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.eyejust.dto.DiagnosticResultDto;
import ru.itis.eyejust.exceptions.DiagnosticException;
import ru.itis.eyejust.models.FileInfo;
import ru.itis.eyejust.security.details.UserEntityDetails;
import ru.itis.eyejust.services.FileInfoService;

import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/diagnostic")
public class DiagnosticController {

    private final FileInfoService fileInfoService;

    @PostMapping
    public String handleFileUpload(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                                   @RequestParam("file1") MultipartFile leftEyeImage,
                                   @RequestParam("file2") MultipartFile rightEyeImage,
                                   Model model) {

        FileInfo fileLeftEye = fileInfoService.saveToStorage(leftEyeImage, userEntityDetails.getUserEntity().getId());
        FileInfo fileRightEye = fileInfoService.saveToStorage(rightEyeImage, userEntityDetails.getUserEntity().getId());

        RestTemplate restTemplate = new RestTemplate();
        String pythonUrl = "http://localhost:5000/process";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject requestBody = new JSONObject();
        requestBody.put("leftEyeFileId", fileLeftEye.getId());
        requestBody.put("rightEyeFileId", fileRightEye.getId());
        requestBody.put("userId", userEntityDetails.getUserEntity().getId());

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toString(), headers);

        log.info("start sending request to ML server");
        ResponseEntity<DiagnosticResultDto> responseEntity = restTemplate.postForEntity(pythonUrl, requestEntity, DiagnosticResultDto.class);
        log.info("got response from server: " + Objects.requireNonNull(responseEntity.getBody()).toString());

        if (responseEntity.getBody().getLeftEyeDiagnosticValue().equals("error")) {
            throw new DiagnosticException("Diagnostic error, change the input data");
        } else {
            String leftEyeDiagnosticResponse = responseEntity.getBody().getLeftEyeDiagnosticValue();
            String rightEyeDiagnosticResponse = responseEntity.getBody().getRightEyeDiagnosticValue();
            String leftEyeCroppedImageIdResponse = responseEntity.getBody().getLeftEyeCroppedImageId();
            String rightEyeCroppedImageIdResponse = responseEntity.getBody().getRightEyeCroppedImageId();
            String leftEyeMaskImageIdResponse = responseEntity.getBody().getLeftEyeMaskImageId();
            String rightEyeMaskImageIdResponse = responseEntity.getBody().getRightEyeMaskImageId();
            String leftEyeCDRResponse = responseEntity.getBody().getLeftEyeCDR();
            String rightEyeCDRResponse = responseEntity.getBody().getRightEyeCDR();

            model.addAttribute("leftEyeDiagnosticValue", leftEyeDiagnosticResponse);
            model.addAttribute("rightEyeDiagnosticValue", rightEyeDiagnosticResponse);
            model.addAttribute("leftEyeImageId", fileLeftEye.getId());
            model.addAttribute("rightEyeImageId", fileRightEye.getId());
            model.addAttribute("leftEyeCroppedImageId", leftEyeCroppedImageIdResponse);
            model.addAttribute("rightEyeCroppedImageId", rightEyeCroppedImageIdResponse);
            model.addAttribute("leftEyeMaskImageId", leftEyeMaskImageIdResponse);
            model.addAttribute("rightEyeMaskImageId", rightEyeMaskImageIdResponse);
            model.addAttribute("leftEyeCDR", leftEyeCDRResponse);
            model.addAttribute("rightEyeCDR", rightEyeCDRResponse);

            model.addAttribute("user", userEntityDetails.getUserEntity());

            return "diagnostic-result";
        }
    }

    @GetMapping
    public String getUploadForm(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                                Model model) {
        model.addAttribute("user", userEntityDetails.getUserEntity());
        return "diagnostic-form";
    }
}
