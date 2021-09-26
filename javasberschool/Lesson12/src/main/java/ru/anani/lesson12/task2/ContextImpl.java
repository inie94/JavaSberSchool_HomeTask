package ru.anani.lesson12.task2;

public class ContextImpl implements Context {

    private final int taskCount;
    private final ThreadPool pool;

    private int completedTaskCount = 0;
    private int failedTaskCount = 0;
    private int interruptedTaskCount = 0;

    public ContextImpl(int taskCount, ThreadPool pool) {
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
//        interruptedTaskCount = pool.shutdown();
    }

    @Override
    public boolean isFinished() {
        return taskCount == completedTaskCount + interruptedTaskCount;
    }
}
