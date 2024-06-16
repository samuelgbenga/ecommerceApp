package main.ecommerce.app.constant;

public class ProdCatQueries {
    public static final String INSERT_CATEGORY =
            "INSERT INTO categories (name, description) VALUES (?, ?)";

    public static final String SELECT_ALL_CATEGORIES =
            "SELECT * FROM categories";

    public static final String SELECT_CATEGORY_BY_ID =
            "SELECT * FROM categories WHERE id = ?";

    public static final String UPDATE_CATEGORY =
            "UPDATE categories SET name = ?, description = ? WHERE id = ?";

    public static final String DELETE_CATEGORY =
            "DELETE FROM categories WHERE id = ?";
}
