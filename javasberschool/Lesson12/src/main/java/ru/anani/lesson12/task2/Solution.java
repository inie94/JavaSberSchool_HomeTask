package ru.anani.lesson12.task2;

import ru.anani.lesson12.task2.threadpool.FixedThreadPool;

public class Solution {

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable1 = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " execute runnable №1");
//                throw new StackOverflowError();
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " execute runnable №2");
        };

        Runnable runnable3 = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " execute runnable №3");
        };

        Runnable runnable4 = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " execute runnable №4");
        };

        Runnable runnable5 = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " execute runnable №5");
        };

        Runnable runnable6 = () -> {
            throw new RuntimeException();
        };


        Runnable callback = () -> System.out.println("Callback is run");


        ExecutionManager manager = new ExecutorManagerImpl(new FixedThreadPool(5));

        Context context = manager.execute(callback, runnable1, runnable2, runnable3, runnable4, runnable5, runnable6);

        context.interrupt();

        Thread.sleep(2000);
        System.out.println("Context is finished: " + context.isFinished());
        System.out.println("Completed tasks: " + context.getCompletedTaskCount());
        System.out.println("Failed tasks: " + context.getFailedTaskCount());
        System.out.println("Interrupted tasks: " + context.getInterruptedTaskCount());

//        threadPool.shutdown();
    }
}
