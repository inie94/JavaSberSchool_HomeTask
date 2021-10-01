package ru.anani.lesson12.task1;

import java.util.concurrent.Callable;

public class Task<T> {

    private final Callable<T> callable;
    private static boolean isPerformed = false;
//    private static boolean isDone = false;


    public Task(Callable<? extends T> callable) {
        this.callable = (Callable<T>) callable;
    }

    public synchronized T get()  {
        T result = null;
        synchronized (callable) {
            if (isPerformed == true) {
                try {
                    System.out.println(Thread.currentThread().getName() + " are waiting");
                    callable.wait();
                    System.out.println(Thread.currentThread().getName() + " are notify");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            isPerformed = true;
        }
        try {
            System.out.println(Thread.currentThread().getName() + " call callable");
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        isPerformed = false;
        synchronized (callable) {
            callable.notify();
        }
        return result;
    }
}

