package ru.itis.eyejust.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import ru.itis.eyejust.models.enums.Status;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "medical_reports")
public class MedicalReport {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "creation_date")
    private String creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "left_eye_image_id")
    private String leftEyeImageDBId;

    @Column(name = "right_eye_image_id")
    private String rightEyeImageDBId;

    @Column(name = "left_eye_diagnostic_value")
    private String leftEyeDiagnosticValue;

    @Column(name = "right_eye_diagnostic_value")
    private String rightEyeDiagnosticValue;

    @Column(name = "left_eye_cdr")
    private String leftEyeCDR;

    @Column(name = "right_eye_cdr")
    private String rightEyeCDR;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "report_pdf_file_id")
    private String reportFileDBId;

}
