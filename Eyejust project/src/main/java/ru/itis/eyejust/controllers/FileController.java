package ru.itis.eyejust.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.eyejust.models.FileInfo;
import ru.itis.eyejust.security.details.UserEntityDetails;
import ru.itis.eyejust.services.FileInfoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final FileInfoService fileInfoService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @AuthenticationPrincipal UserEntityDetails userEntityDetails) {
        FileInfo newFile = fileInfoService.saveToStorage(file, userEntityDetails.getUserEntity().getId());
        return ResponseEntity.ok().body("File uploaded successfully with id: " + newFile.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") String id,
                                          @AuthenticationPrincipal UserEntityDetails userEntityDetails) {
        FileInfo fileInfo = fileInfoService.getFromStorage(id, userEntityDetails.getUserEntity().getId());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileInfo.getId() + "\"")
                .contentType(MediaType.parseMediaType(fileInfo.getFileType()))
                .body(fileInfo.getData().getData());
    }
}

