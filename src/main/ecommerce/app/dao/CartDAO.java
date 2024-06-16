package main.ecommerce.app.dao;

import main.ecommerce.app.connection.DBConnection;
import main.ecommerce.app.constant.CartQueries;
import main.ecommerce.app.enums.CartStatus;
import main.ecommerce.app.model.Cart;
import main.ecommerce.app.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private final DBConnection connection;

    public CartDAO() {
        try {
            this.connection = new DBConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCart(Cart cart) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(CartQueries.INSERT_CART)) {
            pstmt.setInt(1, cart.getUser().getId());
            pstmt.setString(2, String.valueOf(cart.getStatus()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
    }

    public List<Cart> getAllCarts() {
        List<Cart> carts = new ArrayList<>();
        try (Statement stmt = connection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(CartQueries.SELECT_ALL_CARTS)) {
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));

                User user = new User();
                user.setId(rs.getInt("user_id"));
                cart.setUser(user);

                cart.setStatus(CartStatus.valueOf(rs.getString("cart_status")));
                carts.add(cart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carts;
    }

    public List<Cart> getCartsByUserId(int userId) {
        List<Cart> carts = new ArrayList<>();
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(CartQueries.SELECT_CARTS_BY_USER_ID)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Cart cart = new Cart();
                    cart.setId(rs.getInt("id"));

                    User user = new User();
                    user.setId(rs.getInt("user_id"));
                    cart.setUser(user);

                    cart.setStatus(CartStatus.valueOf(rs.getString("cart_status")));
                    carts.add(cart);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carts;
    }

    public Cart getCartById(int id) {
        Cart cart = null;
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(CartQueries.SELECT_CART_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cart = new Cart();
                    cart.setId(rs.getInt("id"));

                    User user = new User();
                    user.setId(rs.getInt("user_id"));
                    cart.setUser(user);

                    cart.setStatus(CartStatus.valueOf(rs.getString("cart_status")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }

    public void updateCartStatus(int cartId, String status) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(CartQueries.UPDATE_CART_STATUS)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, cartId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
    }

    public void deleteCart(int id) {
        try (PreparedStatement pstmt = connection.getConnection().prepareStatement(CartQueries.DELETE_CART)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

