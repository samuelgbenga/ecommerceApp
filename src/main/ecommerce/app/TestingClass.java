package main.ecommerce.app;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.ecommerce.app.dao.*;
import main.ecommerce.app.enums.CartStatus;
import main.ecommerce.app.enums.Role;
import main.ecommerce.app.model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet("/test")
public class TestingClass extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/html");
            out.println("<html><h1>connection establish withe the db</h1></html>");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        Category cat = new Category();
//        CategoryDAO dao = new CategoryDAO();
//
//        cat.setName("cattle");
//        cat.setDescription("obudo cattle rannch");
//        dao.addCategory(cat);


//        Product prod = new Product();
//        prod.setName("cloth");
//        prod.setDescription("cloth line");
//        prod.setPrice(325.5);
//       // prod.setCategory(new Category());
//        ProductDAO prodDao = new ProductDAO();
//        prodDao.deleteProduct(3);

//        User user = new User();
//        user.setUsername("gbenga");
//        user.setPassword("1234");
//        user.setEmail("gbenga@gmail.com");
//        user.setRole(Role.CUSTOMER);
//        UserDAO userDAO = new UserDAO();
//        userDAO.addUser(user);
       // userDAO.deleteUser(1);

//        Cart cart = new Cart();
//        cart.setStatus(CartStatus.COMPLETED);
//        CartDAO cartDAO = new CartDAO();
////
////
//        cartDAO.deleteCart(4);

//        LikeDAO likeDAO = new LikeDAO();
//        Like like = new Like();
//        likeDAO.addLike(like);


    }

}
