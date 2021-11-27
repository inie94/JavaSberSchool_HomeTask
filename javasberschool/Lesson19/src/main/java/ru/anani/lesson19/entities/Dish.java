package ru.anani.lesson19.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dishes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dish_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients;


}
