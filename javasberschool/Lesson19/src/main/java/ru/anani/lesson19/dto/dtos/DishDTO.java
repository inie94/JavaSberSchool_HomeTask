package ru.anani.lesson19.dto.dtos;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishDTO {
    private long id;
    private String name;
    private Set<IngredientDTO> ingredients;
}
