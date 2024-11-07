package ru.itis.deshevin.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"photo", "analogueClass", "drugsCategory", "users"})
@ToString(exclude = {"users"})
@Table(name = "drugs")
public class DrugEntity extends BaseEntity {

    private String title;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private FileInfoEntity photo;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(columnDefinition="TEXT")
    private String composition;

    @Column(columnDefinition="TEXT", name = "side_effects")
    private String sideEffects;

    @Column(columnDefinition="TEXT")
    private String effect;

    @Column(columnDefinition="TEXT")
    private String instruction;

    @Column(columnDefinition="TEXT")
    private String contraindications;

    @Column(columnDefinition="TEXT", name = "release_form")
    private String releaseForm;

    @Column(columnDefinition="TEXT")
    private String manufacturer;

    @Column(columnDefinition="TEXT", name = "storage_conditions")
    private String storageConditions;

    @ManyToOne
    @JoinColumn(name = "analogue_class_id")
    private AnalogueClassEntity analogueClass;

    @ManyToMany(fetch = FetchType.EAGER, cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(
            name = "drugs_category",
            joinColumns = @JoinColumn(name = "drug_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> drugsCategory;

    @ManyToMany(mappedBy = "favorites", cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    private Set<UserEntity> users;

}
