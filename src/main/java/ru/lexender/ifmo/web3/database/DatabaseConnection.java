package ru.lexender.ifmo.web3.database;

import ru.lexender.ifmo.web3.core.DataRow;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
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

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static List<DataRow> getShots(String sessionId) {
        String selectQuery = "SELECT * FROM shots WHERE session = ?";

        List<DataRow> data = new LinkedList<>();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {

            pstmt.setString(1, sessionId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                data.add(new DataRow(
                        rs.getDouble("x"),
                        rs.getDouble("y"),
                        rs.getDouble("r"),
                        rs.getBoolean("result"),
                        rs.getLong("time"),
                        rs.getString("session")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return data;
    }

    public static void saveShot(double x, double y, double r, long time, boolean result, String sessionId) {
        String insertQuery = "INSERT INTO shots(x, y, r, time, result, session) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setDouble(1, x);
            pstmt.setDouble(2, y);
            pstmt.setDouble(3, r);
            pstmt.setLong(4, time);
            pstmt.setBoolean(5, result);
            pstmt.setString(6, sessionId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
