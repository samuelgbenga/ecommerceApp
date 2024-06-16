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

@WebServlet("/register")
public class HandleNewCustomer extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       resp.sendRedirect("/ecommerceApp/users?action=new");

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
        User newUser = new User(username, password, email, Role.valueOf("CUSTOMER"));
        try {
            userDAO.addUser(newUser);
            response.sendRedirect("/ecommerceApp");
        } catch (Exception e) {
            response.sendRedirect("/ecommerceApp/register?message=user already exists");
        }

    }
}
