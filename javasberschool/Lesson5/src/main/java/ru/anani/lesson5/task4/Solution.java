package ru.anani.lesson5.task4;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Solution {

    public static final String MONDAY = "MONDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "WEDNESDAY";
    public static final Integer Num = 67;
    public static final Character CHARACTER = 'S';


    public static void main(String[] args) {
        Class<Solution> solutionClass = Solution.class;

        Arrays.stream(solutionClass.getDeclaredFields())
                .filter(field -> String.class.isAssignableFrom(field.getType()))
                .filter(field -> Modifier.isFinal(field.getModifiers()) && Modifier.isStatic(field.getModifiers()))
                .filter(field -> {
                    try {
                        return field.getName().equals(field.get(solutionClass));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .forEach(field -> System.out.println(field.getName()));
    }
}
