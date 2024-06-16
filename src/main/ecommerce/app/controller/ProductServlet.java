package main.ecommerce.app.controller;



import main.ecommerce.app.dao.ProductDAO;
import main.ecommerce.app.dao.CategoryDAO;
import main.ecommerce.app.model.Product;
import main.ecommerce.app.model.Category;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;


@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        //System.out.println(action);
        if (action == null) {
            action = "wrong-page";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertProduct(request, response);
                    break;
                case "adminDelete":
                    adminDeleteProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
                case "list":
                    listProducts(request, response);
                    break;
                case "get-product-by-id":
                    getProductById(request, response);
                    break;
                default:
                    wrongRequest(request, response);
                    break;

            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }



    private void adminDeleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String userName = request.getParameter("username");

        try {
            productDAO.deleteProduct(id);
            response.sendRedirect("users?action=adminPage&message=product deleted&username=" + userName);
        } catch (Exception e) {
            response.sendRedirect("users?action=adminPage&message=cannot delete product&username=" + userName);

        }
    }

    // get product buy id
    private void getProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product products = productDAO.getProductById(id);
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-page.jsp");
        dispatcher.forward(request, response);
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.getAllProducts();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryDAO.getAllCategories();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productDAO.getProductById(id);
        List<Category> categories = categoryDAO.getAllCategories();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
        request.setAttribute("product", existingProduct);
        dispatcher.forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));

        Category category = categoryDAO.getCategoryById(categoryId);
        Product newProduct = new Product(name, description, price, category.getId());
        productDAO.addProduct(newProduct);
        response.sendRedirect("products");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));

        Category category = categoryDAO.getCategoryById(categoryId);
        Product product = new Product(id, name, description, price, category.getId());
        productDAO.updateProduct(product);
        response.sendRedirect("products");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);

    }

    // redirect to wrong page
    private void wrongRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/wrong-request.jsp");
        dispatcher.forward(request, response);
    }
}

