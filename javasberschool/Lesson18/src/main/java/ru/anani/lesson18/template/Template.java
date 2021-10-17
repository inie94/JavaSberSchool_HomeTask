package ru.anani.lesson18.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.anani.lesson18.entities.Dish;
import ru.anani.lesson18.entities.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Template {

    private final JdbcTemplate template;

    @Autowired
    public Template(JdbcTemplate template) {
        this.template = template;
    }

    public List<Dish> getDishByName(String value) {
        List<Dish> dishes = new ArrayList<>();

        List<Map<String, Object>> listOfDishes = template.queryForList("SELECT * FROM dishes WHERE LOWER(dish_name) LIKE ?", "%" + value.toLowerCase() + "%");
            if (!listOfDishes.isEmpty())
            listOfDishes.forEach(stringObjectMap -> {
                Dish dish = new Dish();
                dish.setId(Long.parseLong(stringObjectMap.get("id").toString()));
                dish.setName((String) stringObjectMap.get("dish_name"));
                dish.setIngredients(new HashMap<>());
                List<Map<String, Object>> recipes = template.queryForList("SELECT * FROM recipes WHERE dish_id = ?", dish.getId());
                recipes.forEach(recipe -> {
                    List<Map<String, Object>> ingredients = template.queryForList("SELECT * FROM ingredients WHERE id = ?", recipe.get("ingredient_id"));
                    ingredients.forEach(ingredient -> dish.getIngredients().put(new Ingredient(Long.parseLong(ingredient.get("id").toString()), ingredient.get("ingredient_name").toString()),
                            Integer.parseInt(recipe.get("weight").toString())));
                });
                dishes.add(dish);
            });

        return dishes;
    }

    public void insertDish(Dish dish) {
        template.update("INSERT INTO dishes(dish_name) VALUES (?)", dish.getName());
        List<Map<String, Object>> dishes = template.queryForList("SELECT * FROM dishes WHERE dish_name = ?", dish.getName());
        dish.setId(Long.parseLong(dishes.get(0).get("id").toString()));
        dish.getIngredients().forEach((ingredient, weight) -> {
            List<Map<String, Object>> list = template.queryForList("SELECT * FROM ingredients WHERE ingredient_name = ?", ingredient.getName());
            if (list.isEmpty()) {
                template.update("INSERT INTO ingredients(ingredient_name) VALUES (?)", ingredient.getName());
                list = template.queryForList("SELECT * FROM ingredients WHERE ingredient_name = ?", ingredient.getName());
            }
            ingredient.setId(Long.parseLong(list.get(0).get("id").toString()));
            template.update("INSERT INTO recipes(dish_id, ingredient_id, weight) VALUES (?, ?, ?)", dish.getId(), ingredient.getId(), weight);
        });
    }

    public void deleteDish(long id) {
        template.update("DELETE FROM dishes WHERE id = ?", id);
    }
}
