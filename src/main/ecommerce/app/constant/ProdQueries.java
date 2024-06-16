package main.ecommerce.app.constant;

public class ProdQueries {
    public static final String INSERT_PRODUCT =
            "INSERT INTO Products (name, description, price, category_id, pictureUrl) VALUES (?, ?, ?, ?, ?)";

    public static final String SELECT_ALL_PRODUCTS =
            "SELECT * FROM Products";

    public static final String SELECT_PRODUCTS_BY_CATEGORY =
            "SELECT * FROM Products WHERE category_id = ?";

    public static final String SELECT_PRODUCT_BY_ID =
            "SELECT * FROM Products WHERE id = ?";

    public static final String UPDATE_PRODUCT =
            "UPDATE Products SET name = ?, description = ?, price = ?, category_id = ?, pictureUrl = ? WHERE id = ?";

    public static final String DELETE_PRODUCT =
            "DELETE FROM Products WHERE id = ?";
}
