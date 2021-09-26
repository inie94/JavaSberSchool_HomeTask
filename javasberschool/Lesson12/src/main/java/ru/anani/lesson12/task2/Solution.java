package ru.anani.lesson12.task2;

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
                System.out.println(Thread.currentThread().getName() + " execute runnable №2");
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

//        ExecutionManager manager = new ExecutorManagerImpl(new ThreadPool(13));
//
//        Context context = manager.execute(callback, new Runnable[]{runnable1, runnable2, runnable3, runnable4, runnable5, runnable6});
//
//        System.out.println("Context is finished: " + context.isFinished());
//        System.out.println("Completed tasks: " + context.getCompletedTaskCount());
//        System.out.println("Failed tasks: " + context.getFailedTaskCount());
//        System.out.println("Interrupted tasks: " + context.getInterruptedTaskCount());

        ThreadPool threadPool = new ThreadPool(4);

        threadPool.execute(runnable1);
        threadPool.execute(runnable2);
        threadPool.execute(runnable3);
        threadPool.execute(runnable4);
        threadPool.execute(runnable5);
        threadPool.execute(runnable6);



//        threadPool.shutdown();
    }
}
