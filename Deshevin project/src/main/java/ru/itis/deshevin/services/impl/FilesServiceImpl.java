package ru.itis.deshevin.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.deshevin.models.FileInfoEntity;
import ru.itis.deshevin.repositories.FilesRepository;
import ru.itis.deshevin.services.FilesService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilesServiceImpl implements FilesService {
    @Value("${files.storage.path}")
    private String storagePath;

    private final FilesRepository filesRepository;

    @Transactional
    @Override
    public Optional<FileInfoEntity> saveFileToStorage(MultipartFile multipartFile) {
        try {
            String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

            String storageFileName = UUID.randomUUID() + extension;

            FileInfoEntity file = FileInfoEntity.builder()
                    .type(multipartFile.getContentType())
                    .originalFileName(multipartFile.getOriginalFilename())
                    .fileDBID(storageFileName)
                    .size((int)multipartFile.getSize())
                    .build();

            Files.copy(multipartFile.getInputStream(), Paths.get(storagePath, file.getFileDBID()));

            filesRepository.save(file);

            return Optional.of(file);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void addFileToResponse(String fileName, HttpServletResponse response) {
        FileInfoEntity file = filesRepository.findByFileDBID(fileName).orElseThrow();
        response.setContentLength(file.getSize());
        response.setContentType(file.getType());
        response.setHeader("Content-Disposition", ": attachment; filename=\"" + URLEncoder.encode(file.getOriginalFileName(), StandardCharsets.UTF_8) + "\"");
        try {
            IOUtils.copy(new FileInputStream(storagePath + "/" + file.getFileDBID()), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
