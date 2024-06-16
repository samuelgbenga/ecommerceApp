package main.ecommerce.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.ecommerce.app.dao.UserDAO;
import main.ecommerce.app.enums.Role;
import main.ecommerce.app.model.User;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/admin-signup")
public class HandleAdminSignup extends HttpServlet {

    private UserDAO userDAO ;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            insertUser(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User newUser = new User(username, password, email, Role.valueOf("ADMIN"));
        userDAO.addUser(newUser);
        response.sendRedirect("/ecommerceApp");
    }
}
