package shopcertificate.dao;

import java.sql.*;
import shopcertificate.db.DatabaseConnection;

public class UserDAO {

    private final DatabaseConnection db;

    public UserDAO(DatabaseConnection db) {
        this.db = db;
    }

    public boolean authenticate(String username, String password) throws SQLException {

        String sql = "SELECT user_id FROM users " +
                     "WHERE username = ? AND password = ? AND active = 1";

        try (Connection c = db.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, username);
            p.setString(2, password);

            try (ResultSet r = p.executeQuery()) {
                return r.next();
            }
        }
    }
}