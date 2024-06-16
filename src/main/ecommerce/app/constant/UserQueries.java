package main.ecommerce.app.constant;

public class UserQueries {
    public static final String INSERT_USER =
            "INSERT INTO Users (username, password, email, role) VALUES (?, ?, ?, ?)";

    public static final String SELECT_ALL_USERS =
            "SELECT * FROM Users";

    public static final String SELECT_USER_BY_ID =
            "SELECT * FROM Users WHERE id = ?";

    public static final String SELECT_USER_BY_USERNAME =
            "SELECT * FROM Users WHERE username = ?";

    public static final String UPDATE_USER =
            "UPDATE Users SET username = ?, password = ?, email = ?, role = ? WHERE id = ?";

    public static final String DELETE_USER =
            "DELETE FROM Users WHERE id = ?";
}
