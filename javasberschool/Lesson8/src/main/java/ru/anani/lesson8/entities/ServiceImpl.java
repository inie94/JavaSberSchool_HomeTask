package ru.anani.lesson8.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {
    @Override
    public List<String> run(String item, double value, Date date) {
        return Arrays.asList("first", "second", "third");
    }

    @Override
    public List<String> work(String item) {
        System.out.println("Service \"work\" method is run.");
        return null;
    }
}
