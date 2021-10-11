package ru.anani.lesson16.source;

import java.sql.SQLException;
import java.util.List;

public interface Source {

    boolean contains(int n) throws SQLException;

    List<Integer> getAllValues() throws SQLException;

    List<Integer> getAllValuesUpToInclusive(int n) throws SQLException;

    void saveAll(List<Integer> result);

    void closeConnection() throws SQLException;
}
