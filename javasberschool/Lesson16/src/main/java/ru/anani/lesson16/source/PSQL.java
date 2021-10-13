package ru.anani.lesson16.source;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PSQL implements Source {

    private static final String URL = "jdbc:postgresql://localhost:5432/fibonacci";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "frS?hq&7";

    private final Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

    public PSQL() throws SQLException {

    }

    @Override
    public boolean contains(int n) {
        try (PreparedStatement statement = connection.prepareStatement(String.format("SELECT EXISTS(SELECT 1 FROM numbers WHERE id=%s)", n))) {
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Integer> getAllValues() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT value FROM numbers")) {
            List<Integer> integers = new ArrayList<>();
            final ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    integers.add(resultSet.getInt("value"));
                }
            return integers;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Integer> getAllValuesUpToInclusive(int n) {
        try (PreparedStatement statement = connection.prepareStatement(String.format("SELECT value FROM numbers WHERE id <= %s", n))) {
            List<Integer> integers = new ArrayList<>();
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                integers.add(resultSet.getInt("value"));
            }
            return integers;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveAll(List<Integer> result) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO numbers(value) VALUES(?)")) {
            for (Integer integer:result) {
                statement.setInt(1, integer);
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
