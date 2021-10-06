package ru.anani.lesson12.task1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class Task<T> {

    private final Callable<T> callable;
    private static boolean isPerformed = false;
    private volatile Map<Callable<T>, T> cache = new HashMap<>();
    private MyRuntimeException myRuntimeException;

    public Task(Callable<? extends T> callable) {
        this.callable = (Callable<T>) callable;
    }

    public T get()  {
        T result = null;
        if (cache.containsKey(callable)) {
            System.out.println(Thread.currentThread().getName() + " get from cache");
            return cache.get(callable);
        } else {
            synchronized (callable) {
                if (isPerformed) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " are waiting");
                        callable.wait();
                        System.out.println(Thread.currentThread().getName() + " are notify");
                        if (myRuntimeException != null)
                            throw myRuntimeException;
                        if (cache.containsKey(callable)) {
                            System.out.println(Thread.currentThread().getName() + " get from cache");
                            synchronized (callable) {
                                callable.notify();
                            }
                            return (T) (Thread.currentThread().getName() + " " + cache.get(callable));
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                isPerformed = true;
            }
            try {
                System.out.println(Thread.currentThread().getName() + " call callable");
                try {
                    result = callable.call();
                } catch (Exception e) {
                    throw new MyRuntimeException(e);
                }
                cache.put(callable, result);
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
}

