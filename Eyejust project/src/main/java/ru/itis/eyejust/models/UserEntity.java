package ru.itis.eyejust.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import ru.itis.eyejust.models.enums.Role;
import ru.itis.eyejust.models.enums.State;
import ru.itis.eyejust.models.enums.Status;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"medicalReports"})
@ToString(exclude = {"medicalReports"})
@Table(name = "patients")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH)
    private Set<MedicalReport> medicalReports;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String patronymic;

    private String gender;

    private String birthdate;

    private String address;

    @Column(columnDefinition = "text", name = "past_illnesses")
    private String pastIllnesses;

    @Column(columnDefinition = "text", name = "chronic_diseases")
    private String chronicDiseases;

    @Column(columnDefinition = "text")
    private String surgeries;

    @Column(columnDefinition = "text", name = "drug_intolerance")
    private String drugIntolerance;

    @Column(columnDefinition = "text", name = "bad_habits")
    private String badHabits;

}
