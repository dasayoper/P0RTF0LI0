package ru.itis.restaurant.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu_items")
public class MenuItem {
    public enum Category {
        DRINK, SNACK, DISH, DESSERT
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer weight;

    private Integer price;

    @ManyToMany(mappedBy = "items")
    private Set<Order> order;

    @Enumerated(value = EnumType.STRING)
    private Category category;
}
