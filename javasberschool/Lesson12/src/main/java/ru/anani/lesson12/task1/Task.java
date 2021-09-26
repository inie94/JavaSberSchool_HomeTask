package ru.anani.lesson12.task1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Task<T> {

//    private final Thread thread;
    private final FutureTask<?> future;
    private final Callable<?> callable;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
        future = new FutureTask<>(callable);
    }

    public synchronized T get()  {
        System.out.println(Thread.currentThread().getName() + " enter to get()!");
        try {
            return (T) callable.call();
        } catch (Exception e) {
            throw new MyRuntimeException(e);
        }
//        T result = null;
//        try {
//             result = (T) future.get();
//        } catch (Exception e) {
//            throw new MyRuntimeException(e);
//        }
//        return result;
    }
}

