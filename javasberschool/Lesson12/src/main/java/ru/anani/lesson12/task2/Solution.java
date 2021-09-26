package ru.anani.lesson12.task2;

import ru.anani.lesson12.task2.threadpool.FixedThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Solution {
    public static int size = 400;

    public static void main(String[] args) {

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " execute runnable №1");
//                throw new StackOverflowError();
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                FutureTask future = new FutureTask(new Callable() {
                    @Override
                    public Object call() throws Exception {
                        System.out.println(Thread.currentThread().getName() + " execute runnable №2");
                        throw new Exception();
                    }
                });
            }
        };

        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " execute runnable №3");
            }
        };

        Runnable runnable4 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " execute runnable №4");
            }
        };

        Runnable runnable5 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " execute runnable №5");
            }
        };

        Runnable runnable6 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " execute runnable №6");
            }
        };

        Runnable callback = new Runnable() {
            @Override
            public void run() {
                System.out.println("Callback is run");
            }
        };

        FutureTask future = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " execute runnable №2");
                throw new Exception();
            }
        });

        ExecutionManager manager = new ExecutorManagerImpl(new FixedThreadPool(13));

        Context context = manager.execute(callback, new Runnable[]{runnable1, runnable2, runnable3, runnable4, runnable5, future});

        System.out.println("Context is finished: " + context.isFinished());
        System.out.println("Completed tasks: " + context.getCompletedTaskCount());
        System.out.println("Failed tasks: " + context.getFailedTaskCount());
        System.out.println("Interrupted tasks: " + context.getInterruptedTaskCount());

//        threadPool.shutdown();
    }
}
