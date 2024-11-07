package ru.itis.restaurant.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    public enum Status {
        DELIVERING, RECEIVED
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String destinationAddress;

    @Column(name = "price")
    private Integer totalPrice;

    @Column(name = "creation_time")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "account")
    @JsonBackReference
    private Account account;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
    private Set<MenuItem> items;

    @Enumerated(value = EnumType.STRING)
    private Status status;

}
