package ru.anani.lesson12.task2.threadpool;

import ru.anani.lesson12.task2.ContextImpl;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FixedThreadPool implements ThreadPool {

    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();
    private final Set<Thread> threads;
    private boolean isInterrupted = false;
    private volatile boolean isDone = false;
    private ContextImpl context = new ContextImpl(this);

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
        new Thread(() -> {
            while (true) {
                if (isInterrupted /*&& threads.stream().allMatch(thread -> thread.getState() == Thread.State.WAITING)*/) {
                    System.out.println("All threads execute tasks and will be interrupted");
//                    threads.forEach(thread -> thread.interrupt());
                    break;
                }
            }
            synchronized (this) {
                isDone = true;
            }
        }).start();
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (queue) {
            queue.offer(runnable);
            context.incrementTaskCount();
            queue.notify();
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public ContextImpl getContext() {
        return context;
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
                    try {
                        task.run();
                        context.incrementCompletedTaskCount();
                    } catch (Exception exception) {
                        context.incrementFailedTaskCount();
                    }
                } else {
                    if (isInterrupted) {
                        Thread.currentThread().interrupt();
                    }
                    synchronized (queue) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            context.incrementInterruptedTaskCount();
                            break;
                        }
                    }
                }
            }
        }
    }
}
