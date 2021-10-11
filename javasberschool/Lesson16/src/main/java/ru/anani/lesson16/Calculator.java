package ru.anani.lesson16;

import ru.anani.lesson16.cache.Cacheable;
import ru.anani.lesson16.source.PSQL;

import java.util.List;

public interface Calculator {

    @Cacheable(PSQL.class)
    List<Integer> fibonacci(int n);
}
