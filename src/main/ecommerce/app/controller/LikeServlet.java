package main.ecommerce.app.controller;




import main.ecommerce.app.dao.LikeDAO;
import main.ecommerce.app.dao.UserDAO;
import main.ecommerce.app.model.Like;
import main.ecommerce.app.model.Product;
import main.ecommerce.app.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/likes")
public class LikeServlet extends HttpServlet {
    private LikeDAO likeDAO;
    private UserDAO userDAO;
    @Override
    public void init() throws ServletException {
        likeDAO = new LikeDAO();
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
       //
        if (action == null) {
            action = "list";
        }
        try {

            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertLike(request, response);
                    break;
                case "delete":
                    deleteLike(request, response);
                    break;
                default:
                    listLikes(request, response);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new ServletException(e);
        }
    }

    // the post handler

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        //
        if (action == null) {
            action = "list";
        }
        try {

            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertLike(request, response);
                    break;
                case "delete":
                    deleteLike(request, response);
                    break;
                default:
                    listLikes(request, response);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new ServletException(e);
        }
    }

    // the other methods
    private void listLikes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("user_id"));
        List<Like> likes = likeDAO.getLikesByUserId(userId);
        request.setAttribute("likes", likes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("like-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertLike(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int productId = Integer.parseInt(request.getParameter("product_id"));
        User user = userDAO.getUserById(userId);
        Product product = new Product();
        product.setId(productId);
       Like newLike = new Like(user, product);
        likeDAO.addLike(newLike);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);


        //response.sendRedirect("/ecommerceApp?showCart=true&username="+user.getUsername()+"&user_id="+user.getId()+"&message=liked");
        //response.getWriter().println("like added");

        //response.sendRedirect("likes?user_id=" + userId);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
        //response.getWriter().write("{\"message\":\"liked\"}");
    }

    private void deleteLike(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int productId = Integer.parseInt(request.getParameter("product_id"));
        likeDAO.deleteLike(userId, productId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }

    //

}

