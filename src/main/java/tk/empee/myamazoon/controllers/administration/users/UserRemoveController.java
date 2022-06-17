package tk.empee.myamazoon.controllers.administration.users;

import tk.empee.myamazoon.model.dao.UsersDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserRemoveController", value = "/personalArea/administration/users/removeUser")
public class UserRemoveController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userID = req.getParameter("userID");
        if(userID != null) {
            try {
                UsersDAO.deleteUser(
                        Long.parseLong(userID)
                );
            } catch (SQLException | NumberFormatException e) {
                resp.setStatus(500);
                throw new RuntimeException("Error while removing a user", e);
            }
        }

    }
}
