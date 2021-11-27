package ru.anani.lesson19.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anani.lesson19.entities.Dish;
import ru.anani.lesson19.repositories.DishRepository;

import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> searchByDishName(String value) {
        return dishRepository.findAllDishByNameContainsIgnoreCase(value);
    }
}
