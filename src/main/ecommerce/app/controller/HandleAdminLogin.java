package main.ecommerce.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.ecommerce.app.dao.UserDAO;
import main.ecommerce.app.model.User;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/admin-login")
public class HandleAdminLogin  extends HttpServlet {
    private UserDAO userDAO ;
    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            login(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }



    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User checkUser = userDAO.getUserByUsername(username);

        if (checkUser != null) {
            if(Objects.equals(checkUser.getPassword(), password)) {
                if(checkUser.getRole().name().equals("ADMIN")) {
                    response.sendRedirect("/ecommerceApp/users?action=adminPage&username="+checkUser.getUsername()+"&message=Admin login success");
                }else {
                    response.sendRedirect("/ecommerceApp/users?action=login&message=You cannot access this page");
                }

            }else {
                response.sendRedirect("/ecommerceApp/users?action=loginAdmin&message=wrong username or password");
            }

        }else {
            response.sendRedirect("/ecommerceApp/users?action=loginAdmin&message=user not found");
        }

    }
}
