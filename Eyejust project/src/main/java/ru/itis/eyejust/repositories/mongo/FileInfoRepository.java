package ru.itis.eyejust.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.eyejust.models.FileInfo;

public interface FileInfoRepository extends MongoRepository<FileInfo, String> {
}
