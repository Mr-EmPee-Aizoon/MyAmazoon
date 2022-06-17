package tk.empee.myamazoon.controllers.authentication;

import tk.empee.myamazoon.model.dao.UsersDAO;
import tk.empee.myamazoon.model.dto.users.User;
import tk.empee.myamazoon.model.utils.mappers.ControllerMappers;
import tk.empee.myamazoon.model.utils.mappers.PageMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "LoginController", value = "/personalArea/login")
public final class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(PageMapper.getURL("login"));
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Optional<User> user = UsersDAO.findUserByCredentials(username, password);
            if (user.isPresent()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user.get());

                response.sendRedirect( ControllerMappers.getAbsoluteURL("adminDashboard", this) );
            } else {
                request.setAttribute("errorMessage", "Username e/o password errata");

                RequestDispatcher dispatcher = request.getRequestDispatcher(PageMapper.getURL("login"));
                dispatcher.forward(request, response);
            }


        } catch (SQLException e) {
            response.setStatus(500);
            throw new RuntimeException("Error while trying to authenticate the user", e);
        }
    }

}
