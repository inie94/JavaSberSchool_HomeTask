package ru.anani.lesson19.dto.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientDTO {
    private String name;
    private double value;
    private String unit;
}
