package ru.anani.lesson12.task2;

import ru.anani.lesson12.task2.threadpool.FixedThreadPool;

public class ContextImpl implements Context {

    private int taskCount = 0;
    private int completedTaskCount = 0;
    private int failedTaskCount = 0;
    private int interruptedTaskCount = 0;

    private final FixedThreadPool threadPool;

    public ContextImpl(FixedThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    public void incrementTaskCount() {
        ++this.taskCount;
    }

    public void incrementInterruptedTaskCount() {
        ++this.interruptedTaskCount;
    }

    public void incrementCompletedTaskCount() {
        ++this.completedTaskCount;
    }

    public void incrementFailedTaskCount() {
        ++this.failedTaskCount;
        --completedTaskCount;
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
        threadPool.interrupt();
    }

    @Override
    public boolean isFinished() {
        return taskCount == completedTaskCount + interruptedTaskCount + failedTaskCount;
    }
}
