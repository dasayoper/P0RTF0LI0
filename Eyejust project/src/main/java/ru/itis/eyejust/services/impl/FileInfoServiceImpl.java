package ru.itis.eyejust.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.eyejust.exceptions.AccessIsDeniedException;
import ru.itis.eyejust.exceptions.FileNotFoundException;
import ru.itis.eyejust.models.FileInfo;
import ru.itis.eyejust.repositories.mongo.FileInfoRepository;
import ru.itis.eyejust.services.FileInfoService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileInfoServiceImpl implements FileInfoService {

    private final FileInfoRepository fileInfoRepository;

    @Override
    public FileInfo saveToStorage(MultipartFile file, UUID userId) {
        log.info("start saving new file");
        try {
            FileInfo newFile = FileInfo.builder()
                    .id(UUID.randomUUID().toString())
                    .fileName(file.getOriginalFilename())
                    .fileSize(String.valueOf(file.getSize()))
                    .fileType(file.getContentType())
                    .data(new Binary(BsonBinarySubType.BINARY, file.getBytes()))
                    .userId(userId.toString())
                    .build();
            fileInfoRepository.insert(newFile);
            log.info("successfully save file with id " + newFile.getId());
            return newFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileInfo getFromStorage(String fileId, UUID userId) {
        FileInfo file = fileInfoRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
        if (userId != null && userId.toString().equals(file.getUserId())) {
            return file;
        } else {
            throw new AccessIsDeniedException("You do not have permission to access this file");
        }
    }
}
