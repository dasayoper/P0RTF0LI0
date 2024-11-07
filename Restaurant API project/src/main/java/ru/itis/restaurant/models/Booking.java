package ru.itis.restaurant.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_bookings")
public class Booking {
    public enum State {
        ACTIVE, CANCELED
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booked_from")
    private String bookedFrom;

    @Column(name = "booked_to")
    private String bookedTo;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Spot spot;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    @Enumerated(value = EnumType.STRING)
    private State state;
}
