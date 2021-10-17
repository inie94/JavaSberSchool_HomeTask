package ru.anani.lesson18;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.anani.lesson18.entities.Dish;
import ru.anani.lesson18.entities.Ingredient;
import ru.anani.lesson18.template.Template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

@Component
public class Terminal {

    private final Template template;

    @Autowired
    public Terminal(Template template) {
        this.template = template;
    }

    public void start() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Write request like: \"get\", \"insert\", \"delete\"");
            System.out.print("Request: ");
            while (true) {
                if(reader.ready()) {
                    String request = reader.readLine();
                    if (request.equals("exit"))
                        break;
                    String cmdName = request.trim().toUpperCase();
                    Request cmd;
                    try {
                        cmd = Request.valueOf(cmdName);
                    } catch (IllegalArgumentException e) {
                        System.out.print("Wrong request. Please write request again: ");
                        continue;
                    }
                    System.out.println("Write dish name");
                    String value = reader.readLine().trim();
                    Dish newDish = new Dish();
                    List<Dish> dishes;
                    switch (cmd) {
                        case GET:
                            dishes = template.getDishByName(value);
                            if(dishes.isEmpty()){
                                System.out.println("Dish not found");
//                                System.out.print("Enter new request: ");
                                continue;
                            } else if (dishes.size() == 1) {
                                System.out.println(dishes.get(0));
                                System.out.print("Request: ");
                                continue;
                            } else {
                                System.out.print("Choose dish by id: ");
                                while (true) {
                                    try {
                                        long id = Long.parseLong(reader.readLine());
                                        if (dishes.stream().noneMatch(dish -> dish.getId() == id))
                                            throw new Exception();
                                        System.out.println(dishes.stream().filter(dish -> dish.getId() == id).findFirst());
                                        System.out.print("Request: ");
                                        break;
                                    } catch (Exception e) {
                                        System.out.print("Id number is wrong. Please write again: ");
                                    }
                                }
                            }
                            break;
                        case DELETE:
                            dishes = template.getDishByName(value);
                            if (dishes.isEmpty()) {
                                System.out.println("Database not contains chosen dish");
                            } else if(dishes.size() == 1) {
                                template.deleteDish(dishes.get(0).getId());
                            } else {
                                System.out.println(dishes);
                                System.out.print("Write dish_id for deleted: ");
                                template.deleteDish(Long.parseLong(reader.readLine()));
                            }
                            System.out.println("Delete complete.");
                            break;
                        case INSERT:
                            // нехватает проверки на наличие рецепта
                            newDish.setName(value);
                            newDish.setIngredients(new HashMap<>());
                            System.out.println("Write ingredients, format like: \"name weight\". If all ingredients insert, write \"end\"");
                            System.out.println("Write ingredient:");
                            while (!(request = reader.readLine()).equals("end")) {
                                Ingredient ingredient = new Ingredient(request.split(" ")[0]);
                                Integer weight = Integer.parseInt(request.split(" ")[1]);
                                newDish.getIngredients().put(ingredient, weight);
                                template.insertDish(newDish);
                            }
                            System.out.println("Insert complete");
                    }
                    System.out.print("Request: ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
