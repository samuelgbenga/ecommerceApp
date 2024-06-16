package main.ecommerce.app.constant;

public class CartItemQueries {
    public static final String INSERT_CART_ITEM =
            "INSERT INTO CartItems (cart_id, product_id, quantity) VALUES (?, ?, ?)";

    public static final String SELECT_CART_ITEMS_BY_CART_ID =
            "SELECT * FROM CartItems WHERE cart_id = ?";

    public static final String UPDATE_CART_ITEM_QUANTITY =
            "UPDATE CartItems SET quantity = ? WHERE id = ?";

    public static final String DELETE_CART_ITEM =
            "DELETE FROM CartItems WHERE id = ?";

    public static final String DELETE_CART_ITEMS_BY_CART_ID =
            "DELETE FROM CartItems WHERE cart_id = ?";

    public static final String GET_ALL_CART_ITEMS =
            "SELECT * FROM CartItems";
}
