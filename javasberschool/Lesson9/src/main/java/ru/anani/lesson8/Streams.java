package ru.anani.lesson8;

import java.util.*;
import java.util.function.*;

public final class Streams<T> {

    private final Collection<? extends T> collection;

    public Streams(Collection<? extends T> collection) {
        this.collection = new ArrayList<>(collection);
    }

    public static Streams of(Collection collection) {
        return new Streams(collection);
    }

    
    public Streams filter(Predicate<? super T> predicate) {
        List<T> result = new ArrayList<>();
        for (T element : collection) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        
        return Streams.of(result);
    }

    public <R> Streams transform(Function<? super T, ? extends R> function) {
        List<R> result = new ArrayList<>();
        for (T element : collection) {
            result.add(function.apply(element));
        }
        return Streams.of(result);
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> functionKey,
                                  Function<? super T, ? extends V> functionValue) {

        Map<K, V> map = new HashMap<>();
        for (T element : collection) {
            map.put(functionKey.apply(element), functionValue.apply(element));
        }

        return map;
    }
}
