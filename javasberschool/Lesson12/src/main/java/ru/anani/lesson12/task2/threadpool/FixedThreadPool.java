package ru.anani.lesson12.task2.threadpool;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedThreadPool implements ThreadPool {

    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();
    private final Set<Thread> threads;
    private boolean isInterrupted = false;
    private volatile AtomicInteger countInterruptedThreads;
    private Thread mainThread;

    public FixedThreadPool(int threadsCount) {
        this.threads = new HashSet<>();
        for (int i = 0; i < threadsCount; i++) {
            threads.add(new TaskExecutor());
        }
    }

    public void interrupt() {
        isInterrupted = true;
    }

    @Override
    public void start() {
        threads.forEach(Thread::start);
        mainThread = new Thread(() -> {
            while (true) {
                if (isInterrupted) {
                    System.out.println("All threads execute tasks and will be interrupted");
                    threads.forEach(thread -> {
                        if (thread.getState() == Thread.State.WAITING) {
                            thread.interrupt();
                            countInterruptedThreads.getAndIncrement();
                        }
                    });
                    while (true) {
                        if(threads.stream().allMatch(thread -> thread.getState() == Thread.State.WAITING || thread.isInterrupted())) {
                            threads.stream().filter(thread -> thread.getState() == Thread.State.WAITING).forEach(thread -> thread.interrupt());
                            break;
                        }
                    }
                    break;
                }
            }
        });
        mainThread.start();
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (queue) {
            queue.offer(runnable);
            queue.notify();
        }
    }

    public AtomicInteger getCountInterruptedThreads() {
        return countInterruptedThreads;
    }

    public Thread getMainThread() {
        return mainThread;
    }

    public void setMainThread(Thread mainThread) {
        this.mainThread = mainThread;
    }

    private final class TaskExecutor extends Thread {

        @Override
        public void run() {
            Runnable task;
            while (this.isAlive()) {
                if (!queue.isEmpty()) {
                    synchronized (queue.peek()) {
                        task = queue.poll();
                    }
                    assert task != null;
                    if (task instanceof FutureTask) {
                        try {
                            ((FutureTask<?>) task).get();
                        } catch (Exception e) {
                            throw new RuntimeException();
                        }
                    } else {
                        task.run();
                    }
                } else {
                    synchronized (queue) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
    }
}
