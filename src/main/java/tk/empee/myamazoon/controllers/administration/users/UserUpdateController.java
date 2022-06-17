package tk.empee.myamazoon.controllers.administration.users;

import tk.empee.myamazoon.model.dao.UsersDAO;
import tk.empee.myamazoon.model.dto.users.Role;
import tk.empee.myamazoon.model.dto.users.User;
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
import java.util.Optional;

@WebServlet(name = "UserUpdateController", value = "/personalArea/administration/users/updateUser")
public class UserUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userID = req.getParameter("userID");
        if(userID != null) {
            try {
                Optional<User> user = UsersDAO.findUserByID(Long.parseLong(userID));
                if(user.isPresent()) {
                    req.setAttribute("user", user.get());

                    RequestDispatcher requestDispatcher = req.getRequestDispatcher(PageMapper.getURL("userForm"));
                    requestDispatcher.forward(req, resp);
                } else {
                    resp.setStatus(501);
                    resp.sendRedirect( PageMapper.getAbsoluteURL("usersList", this) );
                }
            } catch (NumberFormatException | SQLException e) {
                throw new RuntimeException("Error while searching the user ", e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userID = req.getParameter("userID");
        if(userID != null) {
            User user = parseUser(req);
            try {
                user.setId(Long.parseLong(userID));
                UsersDAO.updateUser(user);
            } catch (NumberFormatException | SQLException e) {
                resp.setStatus(500);
                throw new RuntimeException("Error while updating the product", e);
            }

            resp.sendRedirect(ControllerMappers.getAbsoluteURL("usersList", this));
        }

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
