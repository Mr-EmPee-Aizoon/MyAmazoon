package tk.empee.myamazoon.controllers.administration.users;

import tk.empee.myamazoon.model.dao.UsersDAO;
import tk.empee.myamazoon.model.dto.users.Role;
import tk.empee.myamazoon.model.dto.users.User;
import tk.empee.myamazoon.model.utils.mappers.ControllerMappers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserCreateController", value = "/personalArea/administration/users/createUser")
public final class UserCreateController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UsersDAO.saveUser(parseUser(req));
        } catch (SQLException e) {
            resp.setStatus(500);
            throw new RuntimeException(e);
        }

        resp.sendRedirect(ControllerMappers.getAbsoluteURL("usersList", this));
    }


    private User parseUser(HttpServletRequest req) {
        String[] parameters = {
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("role"),
                req.getParameter("username"),
                req.getParameter("password")
        };

        for(Object p : parameters) {
            if(p == null) {
                throw new IllegalArgumentException("A parameter can't be null!");
            }
        }

        User user;
        try {
            user = new User(
                    parameters[0],
                    parameters[1],
                    Role.valueOf(parameters[2]),
                    parameters[3],
                    parameters[4]
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error while parsing a parameter!", e);
        }

        return user;
    }

}
