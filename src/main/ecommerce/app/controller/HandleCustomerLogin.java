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

@WebServlet("/login")
public class HandleCustomerLogin extends HttpServlet {
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

    // business logic for use
    // for now let consider just login in without care of whether
    // the user is an admin or giving any special care to the admin
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // initialize a pencil to write
        //PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //User newUser = new User(username, password);

        User checkUser = userDAO.getUserByUsername(username);

        if (checkUser != null) {
            if(Objects.equals(checkUser.getPassword(), password)) {

                response.sendRedirect("/ecommerceApp?showCart=true&username="+checkUser.getUsername()+"&user_id="+checkUser.getId()+"&message=login success");
            }else {
                response.sendRedirect("/ecommerceApp/users?action=login&message=wrong username or password");
            }

        }else {
            response.sendRedirect("/ecommerceApp/users?action=login&message=user not found");
        }

    }


}
