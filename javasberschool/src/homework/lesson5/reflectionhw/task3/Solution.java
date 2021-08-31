package homework.lesson5.reflectionhw.task3;

import homework.lesson5.reflectionhw.task2.B;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Class<B> bClass = B.class;
        Arrays.stream(bClass.getDeclaredMethods())
                .filter(method -> method.getName().contains("get"))
                .forEach(method -> System.out.println(method.getName()));
    }
}
