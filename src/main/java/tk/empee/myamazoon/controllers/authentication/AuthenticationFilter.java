package tk.empee.myamazoon.controllers.authentication;

import tk.empee.myamazoon.model.dto.users.Role;
import tk.empee.myamazoon.model.dto.users.User;
import tk.empee.myamazoon.model.utils.mappers.ControllerMappers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/personalArea/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        boolean isLoginPage = requestURI.equals( ControllerMappers.getAbsoluteURL("login", request.getServletContext()) );


        if(!isLoginPage) {
            HttpSession session = httpRequest.getSession(false);
            if(session != null) {
                User user = (User) session.getAttribute("user");
                if(user != null) {
                    boolean isAdminPage = requestURI.startsWith(request.getServletContext().getContextPath() + "/personalArea/administration");
                    if (isAdminPage) {
                        if (user.getRole() == Role.ADMIN) {
                            //Admin is logged in and is trying to access an adminPage
                            chain.doFilter(request, response);
                            return;
                        }
                    } else {
                        //User is logged in and is trying to access a userPage
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
        } else {
            //Let the user see the login page
            chain.doFilter(request, response);
            return;
        }

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(501);
        httpResponse.sendRedirect( ControllerMappers.getAbsoluteURL("login", httpRequest.getServletContext()) );
    }

}
