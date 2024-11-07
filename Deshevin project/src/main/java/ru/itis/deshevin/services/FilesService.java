package ru.itis.deshevin.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.deshevin.models.FileInfoEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface FilesService {
    Optional<FileInfoEntity> saveFileToStorage(MultipartFile multipartFile);

    void addFileToResponse(String fileName, HttpServletResponse response);

}
