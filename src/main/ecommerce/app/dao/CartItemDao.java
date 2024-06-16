package main.ecommerce.app.dao;

import main.ecommerce.app.connection.DBConnection;
import main.ecommerce.app.constant.CartItemQueries;
import main.ecommerce.app.model.CartItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CartItemDao {

    private final DBConnection connection;

    public CartItemDao() {
        try {
            this.connection = new DBConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // do insert item to cart
    public void insertCartItem(int cartId, int productId, int quantity) throws SQLException {
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(CartItemQueries.INSERT_CART_ITEM)) {
            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();
        }
    }

    // return cartListItems list

    public List<CartItem> getCartItemsByCartId(int cartId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(CartItemQueries.SELECT_CART_ITEMS_BY_CART_ID)) {
            preparedStatement.setInt(1, cartId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CartItem cartItem = new CartItem(
                            resultSet.getInt("id"),
                            resultSet.getInt("cart_id"),
                            resultSet.getInt("product_id"),
                            resultSet.getInt("quantity")
                    );
                    cartItems.add(cartItem);
                }
            }
        }
        return cartItems;
    }

    public List<CartItem> getALLCartItems() throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(CartItemQueries.GET_ALL_CART_ITEMS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CartItem cartItem = new CartItem(
                            resultSet.getInt("id"),
                            resultSet.getInt("cart_id"),
                            resultSet.getInt("product_id"),
                            resultSet.getInt("quantity")
                    );
                    cartItems.add(cartItem);
                }
            }
        }
        return cartItems;
    }

    // update cart item quantity
    public void updateCartItemQuantity(int id, int quantity) throws SQLException {
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(CartItemQueries.UPDATE_CART_ITEM_QUANTITY)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
    }

    // delete the cart item
    public void deleteCartItem(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(CartItemQueries.DELETE_CART_ITEM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    // delete cartItem by cart id
    public void deleteCartItemsByCartId(int cartId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(CartItemQueries.DELETE_CART_ITEMS_BY_CART_ID)) {
            preparedStatement.setInt(1, cartId);
            preparedStatement.executeUpdate();
        }
    }


}
