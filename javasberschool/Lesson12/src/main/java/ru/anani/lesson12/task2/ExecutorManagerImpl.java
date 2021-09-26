package ru.anani.lesson12.task2;

import ru.anani.lesson12.task2.threadpool.FixedThreadPool;

public class ExecutorManagerImpl implements ExecutionManager{

    private final FixedThreadPool pool;

    public ExecutorManagerImpl(FixedThreadPool pool) {
        this.pool = pool;
        this.pool.start();
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ContextImpl context = new ContextImpl(tasks.length, pool);
        for (Runnable task: tasks) {
            try {
                pool.execute(task);
                context.incrementCompletedTaskCount();
            } catch (Exception exception) {
                context.incrementFailedTaskCount();
            }
        }

        new Thread(callback).start();

        return context;
    }
}
