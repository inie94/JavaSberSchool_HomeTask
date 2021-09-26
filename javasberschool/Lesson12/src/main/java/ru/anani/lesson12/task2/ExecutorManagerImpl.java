package ru.anani.lesson12.task2;

public class ExecutorManagerImpl implements ExecutionManager{

    private final ThreadPool pool;

    public ExecutorManagerImpl(ThreadPool pool) {
        this.pool = pool;
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
