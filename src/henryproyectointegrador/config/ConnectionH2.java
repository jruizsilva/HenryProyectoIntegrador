package henryproyectointegrador.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionH2 {
    Connection conn;

    {
        try {
            conn = DriverManager.getConnection("jdbc:h2:~/test");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return conn;
    }
}
