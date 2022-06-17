package tk.empee.myamazoon.controllers.administration.products;

import tk.empee.myamazoon.model.dao.ProductsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProductsRemoveController", value = "/personalArea/administration/products/removeProduct")
public final class ProductsRemoveController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String prodID = req.getParameter("prodID");
        if(prodID != null) {
            try {
                ProductsDAO.deleteProduct(
                        Long.parseLong(prodID)
                );
            } catch (NumberFormatException | SQLException e) {
                resp.setStatus(500);
                throw new RuntimeException("Error while removing a product", e);
            }
        }

    }
}
