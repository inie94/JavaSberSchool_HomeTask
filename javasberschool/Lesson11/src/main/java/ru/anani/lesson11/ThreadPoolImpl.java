package ru.anani.lesson11;

import java.util.Queue;
import java.util.concurrent.*;

public class ThreadPoolImpl implements ThreadPool{

    private Queue<Runnable> queue = new ConcurrentLinkedQueue<>();
    private final ThreadPoolExecutor executor;

    public ThreadPoolImpl(int threadsCount) {
         this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadsCount);
    }

    public ThreadPoolImpl(int min, int max) {
        this.executor = new ThreadPoolExecutor(
                                min,
                                max,
                                5,
                                TimeUnit.SECONDS,
                                new LinkedBlockingDeque<>(12)
        );
    }

    @Override
    public void start() {
        executor.prestartAllCoreThreads();
    }

    @Override
    public void execute(Runnable runnable) {
        executor.submit(runnable);
    }
}
