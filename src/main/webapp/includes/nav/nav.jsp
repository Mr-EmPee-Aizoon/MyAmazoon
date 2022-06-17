<%@ page import="tk.empee.myamazoon.model.utils.mappers.PageMapper" %>
<%@ page import="tk.empee.myamazoon.model.dto.users.User" %>
<%@ page import="tk.empee.myamazoon.model.dto.users.Role" %>
<%@ page import="tk.empee.myamazoon.model.utils.mappers.ControllerMappers" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="container-fluid p-3">
    <a href="<%= ControllerMappers.getAbsoluteURL("index", application) %>">
        <img class="logo" src="${pageContext.request.contextPath}/images/logo.png">
    </a>

    <div class="container col-10 d-flex align-content-center">
        <form class="container-fluid form-inline">
            <div class="input-group">
                <input class="search-input" type="search">
                <button class="search-button"><i class="fa-solid fa-magnifying-glass"></i></button>
            </div>
        </form>
    </div>
</header>

<nav class="navbar navbar-expand-lg navbar-light">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="<%= ControllerMappers.getAbsoluteURL("index", application) %>">Home</a>
        </li>

        <%
            User user = (User) session.getAttribute("user");
            if(user == null) {
        %>
        <li class="nav-item">
            <a class="nav-link" href="<%= ControllerMappers.getAbsoluteURL("login", application) %>">Login</a>
        </li>
        <%
                } else {
                    if (user.getRole() == Role.ADMIN) {
                        %>
                            <li class="nav-item">
                                <a class="nav-link" href="<%= ControllerMappers.getAbsoluteURL("adminDashboard", application) %>">Dashboard</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="<%= ControllerMappers.getAbsoluteURL("logout", application) %>">Logout</a>
                            </li>
                        <%
                    }
                }

        %>

    </ul>
</nav>