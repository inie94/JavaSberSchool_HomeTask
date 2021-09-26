package ru.anani.lesson12.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Solution {

//    private static final bool mode =

    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();

        Callable callable = () -> {
            int i = 2/0;
            return Thread.currentThread().getName() + " execute callable";
        };



        Task task = new Task(callable);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + " call get()!");
                    System.out.println(task.get());
            }).start();
        }

    }
}
