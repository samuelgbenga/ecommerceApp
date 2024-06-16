package main.ecommerce.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import main.ecommerce.app.dao.CategoryDAO;
import main.ecommerce.app.dao.ProductDAO;
import main.ecommerce.app.dao.UserDAO;
import main.ecommerce.app.enums.Role;
import main.ecommerce.app.model.Category;
import main.ecommerce.app.model.Product;
import main.ecommerce.app.model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;


    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
        categoryDAO = new CategoryDAO();
        productDAO = new ProductDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String userName = request.getParameter("username");
        String postAction = request.getParameter("post-action");
        if (postAction != null) {
            switch (postAction) {
                case "updateProd":
                case "adminPage":
                case "addNewCat":
                case "updateCat":
                    showAdminPage(request, response, userName);
                    break;
                default:
                    wrongRequest(request, response);
                    break;
            }
        }

        if (action == null) {
            action = "wrong-request";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "newAdmin":
                    showNewFormAdmin(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "login":
                    loginForm(request, response);
                    break;
                case "loginAdmin":
                    loginFormAdmin(request, response);
                    break;
                case "adminPage":
                    showAdminPage(request, response, userName);
                    break;
                case "deleteCat":
                    deleteCategory(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateUser(request, response);
                    break;
                case "logout":
                    userLogout(request, response);
                    break;
                case "get-product-by-id":
                    getProductById(request, response);
                    break;
                case "list":
                    listUsers(request, response);
                    break;
                default:
                    wrongRequest(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void wrongRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/wrong-request.jsp");
        dispatcher.forward(request, response);
    }

    private void userLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/ecommerceApp");
    }

    // to return the mapping of product to there individual product
    private Map<Category, List<Product>> getCatMap(){
        Map<Category, List<Product>> catMap = new HashMap<>();
        // get category
        List<Category> cat = categoryDAO.getAllCategories();

        // loops through the individual category
        // and adds there belonging product using mapping pair
        cat.forEach(c->{
          List<Product> prodList = productDAO.getProductsByCategory(c.getId());
          catMap.put(c,prodList);
        });



        return catMap;
    }

    private void showAdminPage(HttpServletRequest request, HttpServletResponse response, String userName)throws ServletException, IOException {
        Map<Category, List<Product>> catMap = getCatMap();
        request.setAttribute("catMap", catMap);
        request.setAttribute("userName", userName);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-page.jsp");
        dispatcher.forward(request, response);
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-list.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
        dispatcher.forward(request, response);
    }

    // new form for admin to sign up
    private void showNewFormAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form-admin.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.getUserById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
        request.setAttribute("user", existingUser);

        dispatcher.forward(request, response);
    }


    private void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login-form.jsp");
        dispatcher.forward(request, response);
    }

    // login form for admin
    private void loginFormAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login-form-admin.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        User user = new User(username, password, email, Role.valueOf(role));
        user.setId(id);
        userDAO.updateUser(user);
        response.sendRedirect("users");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("users");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String postAction = request.getParameter("post-action");
        if (postAction == null) {
            postAction = "list";
        }

        try {
            switch (postAction) {
                case "adminPage": // this is a mistake it should had been "addNewProduct" new product
                    addNewProduct(request, response); // but we will work it for now please. thank you
                    break;
                case "updateProd":
                    updateProd(request, response);
                    break;
                case "addNewCat":
                    addCategory(request, response);
                    break;
                case "updateCat":
                    updateCategory(request, response);
                    break;
                default:
                    wrongRequest(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // update the category and update it to the admin page
    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("update-category_id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String userName = request.getParameter("username");
        Category category = new Category(id, name, description);
        categoryDAO.updateCategory(category);
        showAdminPage(request, response, userName);
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String userName = request.getParameter("username");
        try {
            categoryDAO.deleteCategory(id);
        } catch (Exception e) {
            showAdminPage(request, response, userName);
        }
        showAdminPage(request, response, userName);
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String userName = request.getParameter("username");
        String description = request.getParameter("description");
        Category category = new Category(name, description);
        categoryDAO.addCategory(category);

        showAdminPage(request, response, userName);

    }

    private void updateProd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("product_id"));
        String name = request.getParameter("name");
        String userName = request.getParameter("username");
        String pictureUrl = request.getParameter("update-picture-url");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));

        Product product = new Product(id, name, description, price, categoryId, pictureUrl);
        productDAO.updateProduct(product);

        showAdminPage(request, response, userName);

    }


    // method to Handle post updates
    private void addNewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String userName = request.getParameter("username");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        String pictureUrl = request.getParameter("picture-url");
        Product newProduct = new Product(name, description, price, categoryId, pictureUrl);
        productDAO.addProduct(newProduct);

        showAdminPage(request, response, userName);
    }

    // handle get single product to be updated
    private void getProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Category, List<Product>> catMap = getCatMap();
        int id = Integer.parseInt(request.getParameter("id"));
        Product products = productDAO.getProductById(id);
        request.setAttribute("products", products);
        request.setAttribute("catMap", catMap);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-page.jsp");
        dispatcher.forward(request, response);
    }
}

