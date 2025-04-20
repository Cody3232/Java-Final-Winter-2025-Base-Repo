package org.keyin.user;

import org.keyin.database.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {

    private final UserDao userDao = new UserDao ();
// Fetch and verify
    public User loginForUser (String userName, String password) throws SQLException {
        User user = userDao.getUserByUsername(userName);
        if (user != null && BCrypt.checkpw(password, user.getUserPassword())) {
            return user;
        }
        return null;
    }
// Reg new user, with hashing PW
    public void addUser(User user) throws SQLException {
        String hashed = BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt());
        String sql = "INSERT INTO users (user_name, user_password, user_role) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, hashed);
            pstmt.setString(3, user.getUserRole());
            pstmt.executeUpdate();
             }
    }

    public void deleteUser(String username) throws SQLException {
        userDao.deleteUserByUsername(username);
    }
    
    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }
    
}
