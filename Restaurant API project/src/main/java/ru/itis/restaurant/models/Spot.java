package ru.itis.restaurant.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spots")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_number")
    private Integer tableNumber;

    private Integer seats;

    @OneToMany(mappedBy = "spot", fetch = FetchType.EAGER)
    private List<Booking> bookings;
}
