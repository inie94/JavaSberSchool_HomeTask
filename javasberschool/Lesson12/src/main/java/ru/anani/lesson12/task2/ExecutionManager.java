package ru.anani.lesson12.task2;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
