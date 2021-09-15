package ru.anani.lesson8;

import java.util.*;
import java.util.function.*;

public final class Streams<T> {

    private Collection<? extends T> collection;

    public Streams(Collection<? extends T> collection) {
        this.collection = new ArrayList<>(collection);
    }

    public static Streams of(Collection collection) {
        return new Streams(collection);
    }

    
    public Streams<T> filter(Predicate<? super T> predicate) {
        List<T> result = new ArrayList<>();
        for (T element : collection) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        
        return Streams.of(result);
    }

    public <R> Streams<R> transform(Function<? super T, ? extends R> function) {
        List<R> result = new ArrayList<>();
        for (T element : collection) {
            result.add(function.apply(element));
        }
        return Streams.of(result);
    }

    public <R> Streams<R> map(Function<? super T, ? extends R> mapper) {
        return null;
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> functionKey,
                                  Function<? super T, ? extends V> functionValue) {

        Map<K, V> map = new HashMap<>();
        for (T element : collection) {
            map.put(functionKey.apply(element), functionValue.apply(element));
        }

        return map;
    }

    /*public IntStreams mapToInt(ToIntFunction<? super T> mapper) {
        return null;
    }

    
    public LongStreams mapToLong(ToLongFunction<? super T> mapper) {
        return null;
    }

    
    public DoubleStreams mapToDouble(ToDoubleFunction<? super T> mapper) {
        return null;
    }

    
    public <R> Streams<R> flatMap(Function<? super T, ? extends Streams<? extends R>> mapper) {
        return null;
    }

    
    public IntStreams flatMapToInt(Function<? super T, ? extends IntStreams> mapper) {
        return null;
    }

    
    public LongStreams flatMapToLong(Function<? super T, ? extends LongStreams> mapper) {
        return null;
    }

    
    public DoubleStreams flatMapToDouble(Function<? super T, ? extends DoubleStreams> mapper) {
        return null;
    }

    
    public Streams<T> distinct() {
        return null;
    }

    
    public Streams<T> sorted() {
        return null;
    }

    
    public Streams<T> sorted(Comparator<? super T> comparator) {
        return null;
    }

    
    public Streams<T> peek(Consumer<? super T> action) {
        return null;
    }

    
    public Streams<T> limit(long maxSize) {
        return null;
    }

    
    public Streams<T> skip(long n) {
        return null;
    }

    
    public void forEach(Consumer<? super T> action) {

    }

    
    public void forEachOrdered(Consumer<? super T> action) {

    }

    
    public Object[] toArray() {
        return new Object[0];
    }

    
    public <A> A[] toArray(IntFunction<A[]> generator) {
        return null;
    }

    
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return null;
    }

    
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return Optional.empty();
    }

    
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return null;
    }

    
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return null;
    }

    
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return null;
    }

    
    public Optional<T> min(Comparator<? super T> comparator) {
        return Optional.empty();
    }

    
    public Optional<T> max(Comparator<? super T> comparator) {
        return Optional.empty();
    }

    
    public long count() {
        return 0;
    }

    
    public boolean anyMatch(Predicate<? super T> predicate) {
        return false;
    }

    
    public boolean allMatch(Predicate<? super T> predicate) {
        return false;
    }

    
    public boolean noneMatch(Predicate<? super T> predicate) {
        return false;
    }

    
    public Optional<T> findFirst() {
        return Optional.empty();
    }

    
    public Optional<T> findAny() {
        return Optional.empty();
    }

    
    public Iterator<T> iterator() {
        return null;
    }

    
    public Spliterator<T> spliterator() {
        return null;
    }

    
    public boolean isParallel() {
        return false;
    }

    
    public Streams<T> sequential() {
        return null;
    }

    
    public Streams<T> parallel() {
        return null;
    }

    
    public Streams<T> unordered() {
        return null;
    }

    
    public Streams<T> onClose(Runnable closeHandler) {
        return null;
    }

    
    public void close() {

    }*/
}
