package ru.anani.lesson11.fixed;

public interface ThreadPool {
    void start(); // Запускает потоки. Потоки бездействуют, до тех пор пока не появится новое задание в очереди (см. execute)

    void execute(Runnable runnable); // Складывает это задание в очередь. Освободившийся поток должен выполнить это задание. Каждое задание должны быть выполнено ровно 1 раз
}
