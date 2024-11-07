package ru.itis.deshevin.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.deshevin.enums.Role;
import ru.itis.deshevin.enums.Status;

import javax.persistence.*;
import java.util.Set;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "favorites")
@ToString(exclude = "favorites")
@Table(name = "users")
public class UserEntity extends BaseEntity {

    /**
     * Business logic
     */

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER, cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(
            name = "user_favorite_drugs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id")
    )
    private Set<DrugEntity> favorites;

    /**
     * User info
     */

    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String city;

}
