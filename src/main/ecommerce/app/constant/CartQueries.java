package main.ecommerce.app.constant;

public class CartQueries {
    public static final String INSERT_CART =
            "INSERT INTO Carts (user_id, cart_status) VALUES (?, ?)";

    public static final String SELECT_ALL_CARTS =
            "SELECT * FROM Carts";

    public static final String SELECT_CARTS_BY_USER_ID =
            "SELECT * FROM Carts WHERE user_id = ?";

    public static final String SELECT_CART_BY_ID =
            "SELECT * FROM Carts WHERE id = ?";

    public static final String UPDATE_CART_STATUS =
            "UPDATE Carts SET cart_status = ? WHERE id = ?";

    public static final String DELETE_CART =
            "DELETE FROM Carts WHERE id = ?";

}
