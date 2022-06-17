package tk.empee.myamazoon.controllers.administration.products;

import tk.empee.myamazoon.model.utils.mappers.ControllerMappers;
import tk.empee.myamazoon.model.utils.mappers.PageMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductFormController", value = "/personalArea/administration/products/form")
public final class ProductFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if(action != null) {
            String redirect = null;
            if(action.equals("updateProduct")) {
                redirect = ControllerMappers.getURL("updateProduct");

                req.setAttribute("title", "modifica prodotto");
                req.setAttribute("sendButton", "Modifica");
            } else if(action.equals("createProduct")) {
                redirect = PageMapper.getURL("productForm");

                req.setAttribute("title", "crea prodotto");
                req.setAttribute("sendButton", "Crea");
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher(redirect);
            dispatcher.forward(req, resp);
        }

    }
}
