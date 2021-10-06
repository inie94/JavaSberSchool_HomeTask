package ru.anani.lesson12.task2;


import ru.anani.lesson12.task2.threadpool.FixedThreadPool;

public class ExecutorManagerImpl implements ExecutionManager{

    private volatile boolean isInterrupted = false;
    private final FixedThreadPool pool;
    private Runnable callback;

    public ExecutorManagerImpl(FixedThreadPool pool) {
        this.pool = pool;
        this.pool.start();
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
//        ContextImpl context = new ContextImpl(tasks.length);
        this.callback = callback;
        new Thread(() -> {
            for (Runnable task: tasks) {
                pool.execute(task);
            }
            pool.interrupt();
            while (true) {
                synchronized (pool) {
                    if (pool.isDone()) {
                        new Thread(callback).start();
                        break;
                    }
                }
            }
        }).start();

        return pool.getContext();
    }
}
