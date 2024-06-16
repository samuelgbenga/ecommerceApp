package main.ecommerce.app.dao;



import main.ecommerce.app.connection.DBConnection;
import main.ecommerce.app.constant.UserQueries;
import main.ecommerce.app.enums.Role;
import main.ecommerce.app.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final DBConnection connection;

    public UserDAO() {
        try {
            this.connection = new DBConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(UserQueries.INSERT_USER)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, String.valueOf(user.getRole()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement stmt = connection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(UserQueries.SELECT_ALL_USERS)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.valueOf(rs.getString("role")));
                users.add(user);
            }
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
        return users;
    }

    public User getUserById(int id) {
        User user = null;
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(UserQueries.SELECT_USER_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(Role.valueOf(rs.getString("role")));
                }
            }
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
        return user;
    }

    public User getUserByUsername(String username) {
        User user = null;
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(UserQueries.SELECT_USER_BY_USERNAME)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(Role.valueOf(rs.getString("role")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void updateUser(User user) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(UserQueries.UPDATE_USER)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, String.valueOf(user.getRole()));
            pstmt.setInt(5, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(int id) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(UserQueries.DELETE_USER)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
