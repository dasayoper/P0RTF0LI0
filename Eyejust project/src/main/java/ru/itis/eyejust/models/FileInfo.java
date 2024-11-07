package ru.itis.eyejust.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@ToString()
@Document(collection = "files")
public class FileInfo {

    @Id
    private String id;

    private String fileName;

    private String fileType;

    private String fileSize;

    private Binary data;

    private String userId;
}
