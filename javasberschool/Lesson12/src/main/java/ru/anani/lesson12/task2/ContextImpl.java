package ru.anani.lesson12.task2;

import ru.anani.lesson12.task2.threadpool.FixedThreadPool;

public class ContextImpl implements Context {

    private final int taskCount;
    private final FixedThreadPool pool;

    private int completedTaskCount = 0;
    private int failedTaskCount = 0;
    private int interruptedTaskCount = 0;

    public ContextImpl(int taskCount, FixedThreadPool pool) {
        this.taskCount = taskCount;
        this.pool = pool;
    }


    public void incrementCompletedTaskCount() {
        ++this.completedTaskCount;
    }

    public void incrementFailedTaskCount() {
        ++this.failedTaskCount;
    }

    @Override
    public int getCompletedTaskCount() {
        return completedTaskCount;
    }

    @Override
    public int getFailedTaskCount() {
        return failedTaskCount;
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedTaskCount;
    }

    @Override
    public void interrupt() {
        pool.interrupt();
        while (pool.getMainThread().isAlive()) {
        }
        interruptedTaskCount = pool.getCountInterruptedThreads().get();
    }

    @Override
    public boolean isFinished() {
        return taskCount == completedTaskCount + interruptedTaskCount;
    }
}
