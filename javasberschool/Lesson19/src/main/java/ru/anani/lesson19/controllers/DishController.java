package ru.anani.lesson19.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anani.lesson19.dto.DTOService;
import ru.anani.lesson19.dto.dtos.DishDTO;
import ru.anani.lesson19.services.DishService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("dish")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/search")
    public Set<DishDTO> searchDishes(@RequestParam("value") String value) {
        Set<DishDTO> dishes = new HashSet<>();
        dishService.searchByDishName(value).forEach(dish -> dishes.add(DTOService.toDishDTO(dish)));
        return dishes;
    }
}
