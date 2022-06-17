package tk.empee.myamazoon.controllers;

import tk.empee.myamazoon.model.dao.ProductsDAO;
import tk.empee.myamazoon.model.dto.products.Category;
import tk.empee.myamazoon.model.dto.products.Product;
import tk.empee.myamazoon.model.utils.mappers.PageMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "IndexController", value = "/index")
public class IndexController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Product> products;
            String category = request.getParameter("category");
            if(category != null) {
                products = fetchProductsByCategory(category);
            } else {
                products = ProductsDAO.findProducts();
            }

            request.setAttribute("products", products);
        } catch (SQLException e) {
            response.setStatus(500);
            throw new RuntimeException("Error while retrieving some products", e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                PageMapper.getURL("index")
        );

        dispatcher.forward(request, response);
    }

    private List<Product> fetchProductsByCategory(String category) throws SQLException {
        try {
            return ProductsDAO.findProductsByCategory(
                    Category.valueOf(category.toUpperCase(Locale.ROOT))
            );
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

}
