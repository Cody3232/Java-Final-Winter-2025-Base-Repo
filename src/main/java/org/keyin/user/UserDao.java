package org.keyin.user;

import org.keyin.database.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDao {

public void deleteUserByUsername(String username) throws SQLException {
    String sql = "DELETE FROM users WHERE user_name = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, username);
        int rows = pstmt.executeUpdate();
    }
}

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        DriverManager DatabaseConnector;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("user_password"),
                            rs.getString("user_role")

                    );
                }
            }
        }

        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                    rs.getInt("user_id"),
                rs.getString("user_name"),
                rs.getString("user_password"),
                rs.getString("user_role")
                );
                users.add(user);
            }
        }
        return users;
    }
}
