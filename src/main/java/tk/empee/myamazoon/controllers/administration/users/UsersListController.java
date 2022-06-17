package tk.empee.myamazoon.controllers.administration.users;

import tk.empee.myamazoon.model.dao.UsersDAO;
import tk.empee.myamazoon.model.utils.mappers.PageMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UsersControllerList", value = "/personalArea/administration/users/usersList")
public final class UsersListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("users", UsersDAO.findUsers());
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    PageMapper.getURL("usersList")
            );

            dispatcher.forward(request, response);
        } catch (SQLException e) {
            response.setStatus(500);
            throw new RuntimeException("Error while fetching user data from the db", e);
        }
    }

}
