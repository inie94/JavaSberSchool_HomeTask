package ru.anani.lesson12.task2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class ThreadPool implements Executor {

    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();

    public ThreadPool(int threadsCount) {
        for (int i = 0; i < threadsCount; i++) {
            new Thread(new TaskExecutor()).start();
        }
    }

    @Override
    public void execute(Runnable command) {
        synchronized (queue) {
            queue.offer(command);
            queue.notify();
        }
    }

//    public int shutdown() {
//        return queue.size();
//    }

    private final class TaskExecutor implements Runnable {

        @Override
        public void run() {
            Runnable task;
            while (true) {
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
