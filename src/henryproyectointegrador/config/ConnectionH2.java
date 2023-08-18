package henryproyectointegrador.config;

import java.sql.*;

public class ConnectionH2 {
    private static final String JDBC_URL = "jdbc:h2:~/expenses-app";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("H2 database is connected");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}