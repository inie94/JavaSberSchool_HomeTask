package ru.anani.lesson5.task2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Class<B> bClass = B.class;
        getMethods(bClass).forEach(s -> System.out.println(s));
    }

    public static List<String> getMethods(Class<?> tClass) {
        List<String> methodsNames = new ArrayList<>();
        for (Method method: tClass.getDeclaredMethods()) {
            methodsNames.add(method.getName());
        }
        Class<?> superClass = tClass.getSuperclass();
        if (superClass != null) {
            methodsNames.addAll(getMethods(superClass));
        }
        return methodsNames;
    }
}
