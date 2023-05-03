package planner.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection connection;

    public Connect() {
        if (connection == null) {
            Connection conn;
            try {
                String url = "jdbc:sqlite:Server/src/main/resources/planner.db";

                conn = DriverManager.getConnection(url);
                connection = conn;

                System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
