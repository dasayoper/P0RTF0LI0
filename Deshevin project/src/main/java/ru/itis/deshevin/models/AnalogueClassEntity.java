package ru.itis.deshevin.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "drugs")
@ToString(exclude = "drugs")
@Table(name = "analogue_class")
public class AnalogueClassEntity extends BaseEntity {

    private String title;

    @OneToMany(mappedBy = "analogueClass", cascade = CascadeType.DETACH)
    private Set<DrugEntity> drugs;

}
