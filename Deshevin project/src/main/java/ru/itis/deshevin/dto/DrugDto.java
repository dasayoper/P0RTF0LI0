package ru.itis.deshevin.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DrugDto {
    private UUID id;
    private String title;
    private String description;
    private String composition;
    private String manufacturer;
    private String contraindications;
    private String sideEffects;
    private String releaseForm;
    private String effect;
    private String instruction;
    private String storageConditions;

    private Set<CategoryDto> category;
    private String analogueClass;

    private String drugImageFileDBID;
}
