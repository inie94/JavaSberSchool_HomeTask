package ru.anani.lesson11.fixed;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FixedThreadPool implements ThreadPool {

    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();
    private final Set<Thread> threads;

    public FixedThreadPool(int threadsCount) {
        this.threads = new HashSet<>();
        for (int i = 0; i < threadsCount; i++) {
            this.threads.add(new TaskExecutor());
        }
    }

    @Override
    public void start() {
        threads.forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (queue) {
            queue.offer(runnable);
            queue.notify();
        }
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
                    task.run();
                } else {
                    synchronized (queue) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
