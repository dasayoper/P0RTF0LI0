package ru.itis.eyejust.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.eyejust.models.FileInfo;

import java.util.UUID;

public interface FileInfoService {

    FileInfo saveToStorage(MultipartFile file, UUID userId);

    FileInfo getFromStorage(String fileId, UUID userId);
}
