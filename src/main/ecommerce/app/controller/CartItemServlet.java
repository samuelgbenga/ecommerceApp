package main.ecommerce.app.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.ecommerce.app.dao.CartItemDao;
import main.ecommerce.app.model.Cart;
import main.ecommerce.app.model.CartItem;
import main.ecommerce.app.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cartItem")
public class CartItemServlet extends HttpServlet {
    CartItemDao cartItemDao;

    @Override
    public void init() throws ServletException {
        cartItemDao = new CartItemDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertCartItem(request, response);
                    break;
                case "delete":
                    deleteCartItem(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateCartItem(request, response);
                    break;
                default:
                    listCartItems(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listCartItems(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<CartItem> cartItems = cartItemDao.getALLCartItems();
        request.setAttribute("cartItems", cartItems);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cartItem-list.jsp");
        dispatcher.forward(request, response);
    }

    private void updateCartItem(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void deleteCartItem(HttpServletRequest request, HttpServletResponse response) {

    }

    private void insertCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int cartId = Integer.parseInt(request.getParameter("cart_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        cartItemDao.insertCartItem(cartId, productId, quantity);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
