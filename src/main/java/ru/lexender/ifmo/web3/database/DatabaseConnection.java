package ru.lexender.ifmo.web3.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String PROPERTIES_FILE = "db.properties";
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            Properties prop = new Properties();
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find " + PROPERTIES_FILE);
            }
            prop.load(input);
            URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.user");
            PASSWORD = prop.getProperty("db.password");

            Class.forName("org.postgresql.Driver");
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException("Failed to load database properties or PostgreSQL driver", ex);
        }
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void saveShot(double x, double y, double r, long time, boolean result) {
        String insertQuery = "INSERT INTO shots(x, y, r, time, result) VALUES (?, ?, ?, ?, ?)";

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
