package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConfig {
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASS");
    
    private static Connection connection;

    private static void init() throws SQLException {
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
	}
    
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
			init();
		}
        return connection;
    }
}
