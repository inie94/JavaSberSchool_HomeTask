package ru.anani.lesson6.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator <T> implements Iterator<T> {
    private T[] array;
    private int itemCount;
    private int index;

    public MyIterator(T[] array) {
        this.array = array;
        this.itemCount = array.length;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index >= itemCount - 1)
            return false;
        return true;
    }

    @Override
    public T next() {
        if (itemCount <= 0 || !this.hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }
}
