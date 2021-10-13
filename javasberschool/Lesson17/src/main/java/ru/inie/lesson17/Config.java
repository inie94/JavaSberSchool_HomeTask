package ru.inie.lesson17;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan()
public class Config {

    @Autowired
    AnimalService service;
    @Bean
    public Animal animal() {
        return new Animal();
    }

}
