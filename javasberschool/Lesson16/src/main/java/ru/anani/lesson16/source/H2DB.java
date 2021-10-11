package ru.anani.lesson16.source;

import java.util.List;

public class H2DB implements Source {
    @Override
    public boolean contains(int n) {
        return false;
    }

    @Override
    public List<Integer> getAllValues() {
        return null;
    }

    @Override
    public List<Integer> getAllValuesUpToInclusive(int n) {
        return null;
    }

    @Override
    public void saveAll(List<Integer> result) {

    }

    @Override
    public void closeConnection() {

    }
}
