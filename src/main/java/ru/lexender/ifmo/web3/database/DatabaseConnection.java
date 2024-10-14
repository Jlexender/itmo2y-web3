package ru.lexender.ifmo.web3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres"; // URL вашей базы данных
    private static final String USER = "alex"; // Имя пользователя для базы данных
    private static final String PASSWORD = "0000"; // Пароль для базы данных

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load PostgreSQL driver", e);
        }
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void saveShot(double x, double y, double r, long time, boolean result) {
        String insertQuery = "INSERT INTO shots (x, y, r, time, result) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setDouble(1, x);          // Устанавливаем значение для X
            pstmt.setDouble(2, y);       // Устанавливаем значение для Y
            pstmt.setDouble(3, r);       // Устанавливаем значение для R
            pstmt.setLong(4, time); // Устанавливаем значение для time
            pstmt.setBoolean(5, result);  // Устанавливаем результат

            pstmt.executeUpdate();       // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
