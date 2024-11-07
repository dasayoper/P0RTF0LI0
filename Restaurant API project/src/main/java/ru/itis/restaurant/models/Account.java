package ru.itis.restaurant.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    public enum Role {
        USER, ADMIN, MANAGER
    };

    public enum State {
        NOT_CONFIRMED, CONFIRMED, BANNED
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    private String password;

    @Column(name = "confirm_code")
    private String confirmCode;

    @OneToMany(mappedBy = "account")
    @JsonManagedReference
    private Set<Order> orders;

    @OneToMany(mappedBy = "account")
    @JsonManagedReference
    private Set<Booking> bookings;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

}
