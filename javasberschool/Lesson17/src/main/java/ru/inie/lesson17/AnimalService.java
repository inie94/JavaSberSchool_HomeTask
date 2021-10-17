package ru.inie.lesson17;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.inie.Animal;

@Component
public class AnimalService {

    @Autowired
    private Animal animal;

    public void voice() {
        animal.roar();
    }
}
