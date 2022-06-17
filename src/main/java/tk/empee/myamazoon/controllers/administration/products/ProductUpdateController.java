package tk.empee.myamazoon.controllers.administration.products;

import tk.empee.myamazoon.model.dao.ProductsDAO;
import tk.empee.myamazoon.model.dto.products.Category;
import tk.empee.myamazoon.model.dto.products.Product;
import tk.empee.myamazoon.model.utils.mappers.ControllerMappers;
import tk.empee.myamazoon.model.utils.mappers.PageMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@WebServlet(name = "ProductUpdateController", value = "/personalArea/administration/products/updateProduct")
public final class ProductUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prodID = req.getParameter("prodID");
        if(prodID != null) {
            try {
                Optional<Product> product = ProductsDAO.findProductByID(Long.parseLong(prodID));
                if(product.isPresent()) {
                    req.setAttribute("product", product.get());
                } else {
                    resp.setStatus(501);
                    resp.sendRedirect( PageMapper.getAbsoluteURL("productsList", this) );
                }

                RequestDispatcher requestDispatcher = req.getRequestDispatcher(PageMapper.getURL("productForm"));
                requestDispatcher.forward(req, resp);
            } catch (NumberFormatException | SQLException e) {
                throw new RuntimeException("Error while searching the product id " + prodID, e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String prodID = req.getParameter("prodID");
        if(prodID != null) {
            Product product = parseProduct(req);
            try {
                product.setId(Long.parseLong(prodID));
                ProductsDAO.updateProduct(product);
            } catch (NumberFormatException | SQLException e) {
                resp.setStatus(500);
                throw new RuntimeException("Error while updating the product", e);
            }

            resp.sendRedirect(ControllerMappers.getAbsoluteURL("productsList", this));
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
