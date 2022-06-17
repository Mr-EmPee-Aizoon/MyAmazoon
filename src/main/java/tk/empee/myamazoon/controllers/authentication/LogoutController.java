package tk.empee.myamazoon.controllers.authentication;

import tk.empee.myamazoon.model.utils.mappers.ControllerMappers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutController", value = "/personalArea/logout")
public final class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if(session != null) {
            resp.sendRedirect(ControllerMappers.getAbsoluteURL("index", this));
            session.invalidate();
        }
    }

}
