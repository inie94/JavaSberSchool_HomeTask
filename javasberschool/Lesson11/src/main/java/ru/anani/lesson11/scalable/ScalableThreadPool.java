package ru.anani.lesson11.scalable;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ScalableThreadPool implements ThreadPool {

    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();
    private final Set<Thread> threads;
    private final int min;
    private final int max;

    public ScalableThreadPool(int min, int max) {
        this.min = min;
        this.max = max;
        this.threads = new HashSet<>();
        for (int i = 0; i < this.min; i++) {
            this.threads.add(new TaskExecutor());
        }
    }

    @Override
    public void start() {
        threads.forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) {
        System.out.println("Queue size: " + queue.size());
        synchronized (queue) {
            queue.offer(runnable);
            if((threads.stream().noneMatch(thread -> thread.getState() == Thread.State.WAITING)) && threads.size() < max) {
                Thread thread = new TaskExecutor();
                System.out.println(thread.getName() + " is append!");
                this.threads.add(thread);
                thread.start();
            }
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
                    if (threads.size() > min) {
                        threads.remove(this);
                        System.out.println(this.getName() + " is deleted!");
                        break;
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
}
