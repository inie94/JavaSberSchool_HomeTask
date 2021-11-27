package ru.anani.lesson19.dto;

import ru.anani.lesson19.dto.dtos.DishDTO;
import ru.anani.lesson19.dto.dtos.IngredientDTO;
import ru.anani.lesson19.entities.Dish;
import ru.anani.lesson19.entities.Ingredient;

import java.util.HashSet;
import java.util.Set;

public class DTOService {

    public static DishDTO toDishDTO(Dish dish) {
        Set<IngredientDTO> ingredients = new HashSet<>();

        dish.getIngredients().forEach(ingredient -> {
            ingredients.add(toIngredientDTO(ingredient));
        });

        return DishDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .ingredients(ingredients)
                .build();
    }

    private static IngredientDTO toIngredientDTO(Ingredient ingredient) {
        return IngredientDTO.builder()
                .name(ingredient.getProduct().getName())
                .value(ingredient.getValue())
                .unit(ingredient.getUnit().getUnitName())
                .build();
    }

}
