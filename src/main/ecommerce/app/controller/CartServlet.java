package main.ecommerce.app.controller;



import main.ecommerce.app.dao.CartDAO;
import main.ecommerce.app.enums.CartStatus;
import main.ecommerce.app.model.Cart;
import main.ecommerce.app.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/carts")
public class CartServlet extends HttpServlet {
    private CartDAO cartDAO;

    @Override
    public void init() throws ServletException {
        cartDAO = new CartDAO();
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
                    insertCart(request, response);
                    break;
                case "delete":
                    deleteCart(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateCart(request, response);
                    break;
                default:
                    listCarts(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listCarts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cart> carts = cartDAO.getAllCarts();
        request.setAttribute("carts", carts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cart existingCart = cartDAO.getCartById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart-form.jsp");
        request.setAttribute("cart", existingCart);
        dispatcher.forward(request, response);
    }

    private void insertCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String status = request.getParameter("status");

        User user = new User();
        user.setId(userId);
        Cart newCart = new Cart(user, status);
        //System.out.println(status);
        if(cartDAO.getCartsByUserId(userId).isEmpty()) {
            cartDAO.addCart(newCart);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        Cart cart = cartDAO.getCartById(id);
        cart.setStatus(CartStatus.valueOf(status));
        cartDAO.updateCartStatus(cart.getId(), status);
        response.sendRedirect("carts");
    }

    private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        cartDAO.deleteCart(id);
        response.sendRedirect("carts");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
