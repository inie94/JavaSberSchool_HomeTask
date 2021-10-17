package ru.anani.lesson18.entities;

import java.util.Map;

public class Dish {
    long id;
    String name;
    Map<Ingredient, Integer> ingredients;

    public Dish() {
    }

    public Dish(long id, String name, Map<Ingredient, Integer> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
