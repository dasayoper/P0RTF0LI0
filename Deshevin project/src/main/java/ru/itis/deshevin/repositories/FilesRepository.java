package ru.itis.deshevin.repositories;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.deshevin.models.FileInfoEntity;

import java.util.Optional;
import java.util.UUID;

public interface FilesRepository extends JpaRepository<FileInfoEntity, UUID> {
    Optional<FileInfoEntity> findByFileDBID(String fileName);
}
