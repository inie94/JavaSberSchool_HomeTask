package ru.anani.lesson12.task1;

public class MyRuntimeException extends RuntimeException {
    private final Exception exception;

    public MyRuntimeException(Exception exception) {
        this.exception = exception;
    }
}
