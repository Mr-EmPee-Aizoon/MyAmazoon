package tk.empee.myamazoon.controllers.administration.products;

import tk.empee.myamazoon.model.dao.ProductsDAO;
import tk.empee.myamazoon.model.dto.products.Category;
import tk.empee.myamazoon.model.dto.products.Product;
import tk.empee.myamazoon.model.utils.mappers.ControllerMappers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet(name = "ProductCreateController", value = "/personalArea/administration/products/createProduct")
public final class ProductCreateController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product;

        try {
            product = parseProduct(req);
        } catch (IllegalArgumentException e) {
            resp.setStatus(500);
            throw new RuntimeException("Error while parsing a product!", e);
        }

        try {
            long prodID = ProductsDAO.saveProduct(product);

            product.setId(prodID);
            resp.sendRedirect(
                    ControllerMappers.getAbsoluteURL("productsList", this)
            );
        } catch (SQLException e) {
            resp.setStatus(500);
            throw new RuntimeException(e);
        }
    }

    private Product parseProduct(HttpServletRequest req) {
        String[] parameters = {
                req.getParameter("title"),
                req.getParameter("description"),
                req.getParameter("category"),
                req.getParameter("price"),
                req.getParameter("date")
        };

        for(Object p : parameters) {
            if(p == null) {
                throw new IllegalArgumentException("A parameter can't be null!");
            }
        }

        Product product;
        try {
            product = Product.create(
                        parameters[0],
                        parameters[1],
                        Category.valueOf(parameters[2]),
                        Double.parseDouble(parameters[3]),
                        LocalDate.parse(parameters[4])
                );
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw new IllegalArgumentException("Error while parsing a parameter!", e);
        }

        return product;
    }

}
