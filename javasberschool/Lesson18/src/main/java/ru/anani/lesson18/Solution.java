package ru.anani.lesson18;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.anani.lesson18.config.Config;

public class Solution {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Terminal terminal = context.getBean(Terminal.class);
        terminal.start();
    }
}
