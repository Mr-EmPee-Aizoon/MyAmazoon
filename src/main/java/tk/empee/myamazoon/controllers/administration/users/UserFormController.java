package tk.empee.myamazoon.controllers.administration.users;

import tk.empee.myamazoon.model.utils.mappers.ControllerMappers;
import tk.empee.myamazoon.model.utils.mappers.PageMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserFormController", value = "/personalArea/administration/users/form")
public class UserFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String action = req.getParameter("action");
        if(action != null) {
            String redirect = null;
            if(action.equals("updateUser")) {
                redirect = ControllerMappers.getURL("updateUser");

                req.setAttribute("title", "modifica utente");
                req.setAttribute("sendButton", "Modifica");
            } else if(action.equals("createUser")) {
                redirect = PageMapper.getURL("userForm");

                req.setAttribute("title", "crea utente");
                req.setAttribute("sendButton", "Crea");
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher(redirect);
            dispatcher.forward(req, resp);
        }

    }
}
