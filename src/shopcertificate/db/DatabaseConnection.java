package shopcertificate.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/student_certificate_db?serverTimezone=UTC";

    private static final String USER = "root";

    private static final String PASSWORD = "Vinayrajput@7100";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}