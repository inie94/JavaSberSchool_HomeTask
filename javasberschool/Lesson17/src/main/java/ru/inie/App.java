package ru.inie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.inie.lesson17.AnimalService;
import ru.inie.lesson17.Config;


public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        AnimalService animalService = context.getBean(AnimalService.class);
        animalService.voice();
    }

}
