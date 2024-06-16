package main.ecommerce.app.dao;



import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.ecommerce.app.connection.DBConnection;
import main.ecommerce.app.constant.ProdCatQueries;
import main.ecommerce.app.model.Category;

public class CategoryDAO {
    private final DBConnection connection;

    public CategoryDAO() {
        try {
            this.connection = new DBConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCategory(Category category) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(ProdCatQueries.INSERT_CATEGORY)) {
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Statement stmt = connection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(ProdCatQueries.SELECT_ALL_CATEGORIES)) {
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public Category getCategoryById(int id) {
        Category category = null;
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(ProdCatQueries.SELECT_CATEGORY_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    category.setDescription(rs.getString("description"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    public void updateCategory(Category category) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(ProdCatQueries.UPDATE_CATEGORY)) {
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.setInt(3, category.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCategory(int id) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(ProdCatQueries.DELETE_CATEGORY)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

