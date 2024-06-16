package main.ecommerce.app.dao;

import main.ecommerce.app.connection.DBConnection;
import main.ecommerce.app.constant.LikeQueries;
import main.ecommerce.app.model.Like;
import main.ecommerce.app.model.Product;
import main.ecommerce.app.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikeDAO {
    private final DBConnection connection;

    public LikeDAO() {
        try {
            this.connection = new DBConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addLike(Like like) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(LikeQueries.INSERT_LIKE)) {
            pstmt.setInt(1, like.getUser().getId());
            pstmt.setInt(2, like.getProduct().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Like> getLikesByUserId(int userId) {
        List<Like> likes = new ArrayList<>();
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(LikeQueries.SELECT_LIKES_BY_USER_ID)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Like like = new Like();
                    like.setId(rs.getInt("id"));

                    User user = new User();
                    user.setId(rs.getInt("user_id"));
                    like.setUser(user);

                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    like.setProduct(product);

                    likes.add(like);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return likes;
    }

    public List<Like> getLikesByProductId(int productId) {
        List<Like> likes = new ArrayList<>();
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(LikeQueries.SELECT_LIKES_BY_PRODUCT_ID)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Like like = new Like();
                    like.setId(rs.getInt("id"));

                    User user = new User();
                    user.setId(rs.getInt("user_id"));
                    like.setUser(user);

                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    like.setProduct(product);

                    likes.add(like);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return likes;
    }

    public void deleteLike(int userId, int productId) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(LikeQueries.DELETE_LIKE)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
