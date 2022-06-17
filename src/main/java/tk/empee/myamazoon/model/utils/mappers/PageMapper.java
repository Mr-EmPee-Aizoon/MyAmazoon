package tk.empee.myamazoon.model.utils.mappers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;

public final class PageMapper {

    private static HashMap<String, String> urls = new HashMap<>();

    static {
        urls.put("index", "/index.jsp");

        urls.put("login", "/personalArea/login.jsp");
        urls.put("adminDashboard", "/personalArea/administration/dashboard.jsp");
        urls.put("usersList", "/personalArea/administration/users/usersList.jsp");
        urls.put("userForm", "/personalArea/administration/users/userForm.jsp");

        urls.put("productsList", "/personalArea/administration/products/productsList.jsp");
        urls.put("productForm", "/personalArea/administration/products/productForm.jsp");

    }

    public static String getURL(String key) {
        return urls.get(key);
    }

    public static String getAbsoluteURL(String key, HttpServlet servlet) {
        return servlet.getServletContext().getContextPath() + urls.get(key);
    }
    public static String getAbsoluteURL(String key, ServletContext context) {
        return context.getContextPath() + urls.get(key);
    }

}
