package main.ecommerce.app.dao;



import main.ecommerce.app.connection.DBConnection;
import main.ecommerce.app.constant.ProdQueries;
import main.ecommerce.app.model.Category;
import java.sql.*;
import java.util.ArrayList;
import main.ecommerce.app.model.Product;
import java.util.List;

public class ProductDAO {
    private final DBConnection connection;

    public ProductDAO() {
        try {
            this.connection = new DBConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addProduct(Product product) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(ProdQueries.INSERT_PRODUCT)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getCategoryId());
            pstmt.setString(5, product.getPictureUrl());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Statement stmt = connection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(ProdQueries.SELECT_ALL_PRODUCTS)) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setPictureUrl(rs.getString("pictureUrl"));

                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                // Fetch category details if needed
                product.setCategoryId(category.getId());

                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(ProdQueries.SELECT_PRODUCTS_BY_CATEGORY)) {
            pstmt.setInt(1, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setPictureUrl(rs.getString("pictureUrl"));
                    Category category = new Category();
                    category.setId(rs.getInt("category_id"));
                    // Fetch category details if needed
                    product.setCategoryId(category.getId());

                    products.add(product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public Product getProductById(int id) {
        Product product = null;
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(ProdQueries.SELECT_PRODUCT_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));

                    Category category = new Category();
                    category.setId(rs.getInt("category_id"));
                    // Fetch category details if needed
                    product.setCategoryId(category.getId());
                }
            }
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
        return product;
    }

    public void updateProduct(Product product) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(ProdQueries.UPDATE_PRODUCT)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getCategoryId());
            pstmt.setString(5, product.getPictureUrl());
            pstmt.setInt(6, product.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(int id) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(ProdQueries.DELETE_PRODUCT)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

