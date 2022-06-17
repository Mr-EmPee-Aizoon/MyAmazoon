<%@ page import="tk.empee.myamazoon.model.utils.mappers.ControllerMappers" %>
<aside>
    <ul>
        <li>
            <a href="<%= ControllerMappers.getAbsoluteURL("usersList", application) %>"><i class="fa-solid fa-users"></i></a>
        </li>
        <li>
            <a href="<%= ControllerMappers.getAbsoluteURL("productsList", application) %>"><i class="fa-solid fa-cubes"></i></a>
        </li>
    </ul>
</aside>