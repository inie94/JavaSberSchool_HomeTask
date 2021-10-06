package ru.anani.lesson12.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Solution {

//    private static final bool mode =

    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();

        Callable callable = () -> {
//            int i = 2/0;
            Thread.sleep(2000);
            return "execute callable";
        };
        Callable callable1 = () -> {
            throw new Exception();
        };



        Task task = new Task(callable);
        Task task1 = new Task(callable1);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " are created!");
                System.out.println(task.get());
            }).start();
        }
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " are created!");
            System.out.println(task1.get());
        }).start();
    }
}
