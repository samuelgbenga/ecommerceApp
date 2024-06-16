package main.ecommerce.app.constant;

public class LikeQueries {
    public static final String INSERT_LIKE =
            "INSERT INTO Likes (user_id, product_id) VALUES (?, ?)";

    public static final String SELECT_LIKES_BY_USER_ID =
            "SELECT * FROM Likes WHERE user_id = ?";

    public static final String SELECT_LIKES_BY_PRODUCT_ID =
            "SELECT * FROM Likes WHERE product_id = ?";

    public static final String DELETE_LIKE =
            "DELETE FROM Likes WHERE user_id = ? AND product_id = ?";
}
